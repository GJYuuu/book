package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Type;
import com.example.exception.CustomException;
import com.example.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/type")
public class TypeController {
    @Resource
    private TypeService typeService;

    @GetMapping
    public Result findAll(){

        return Result.success(typeService.findAll());
    }//查询所有分类信息

    @PostMapping
    public Result save(@RequestBody Type type){//有一样的参数就注解请求体 form少一个密码
        if(type.getId()==null){
            typeService.add(type);
        }else{
            typeService.update(type);
        }
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Type> info=typeService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){//由路径上接收的id

        typeService.delete(id);
        return Result.success();
    }

    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<Type> list){
        for(Type type:list){//遍历删除
            typeService.delete(type.getId());
        }
        return Result.success();
    }

    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        //一行一行组装数据装到list里
        //每一行数据对应数据库表中的一行数据，也就是对应java的一个实体类Type
        //如何知道某一列就是对应某个表头?要映射数据，需要一个map<key,value>,把这个塞到list里

        //1.从数据库中查询所有数据
        List<Type> all=typeService.findAll();
        if(CollectionUtil.isEmpty(all)){
            throw new CustomException("未找到数据");
        }
        //2.定义一个list，存储处理之后的数据
        List<Map<String,Object>> list=new ArrayList<>(all.size());

        //3.定义map<key,value>，遍历每一条数据，然后封装到map里,把map塞到list里
        for(Type type:all){
            Map<String,Object> row=new HashMap<>();
            row.put("分类编号",type.getId());
            row.put("分类名称",type.getName());

            list.add(row);
        }

        //4.创建一个excelWriter，把list数据用这个writer写出来
        ExcelWriter wr= ExcelUtil.getWriter(true);
        wr.write(list,true);
        //5.把excel下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=type.xlsx");//文件名是type
        ServletOutputStream out=response.getOutputStream();
        wr.flush(out,true);
        wr.close();
        IoUtil.close(System.out);

        return Result.success();
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException{
        List<Type> infoList=ExcelUtil.getReader(file.getInputStream()).readAll(Type.class);//读文件并转化为Type对象,注意实体类要跟上@Alias("")否则没法映射上
        if(!CollectionUtil.isEmpty(infoList)){
            for(Type type:infoList){
                try{
                    typeService.add(type);
                }catch (Exception e){
                    e.printStackTrace();
                }//导入时报错不影响继续for循环
            }
        }
        return Result.success();
    }
}
