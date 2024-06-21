package com.example.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Book;
import com.example.entity.Lend;
import com.example.entity.Params;
import com.example.service.AdminService;
import com.example.service.BookService;
import com.example.service.LendService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @Resource
    private LendService lendService;

    @Resource
    private AdminService adminService;

    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Book> info=bookService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLog("修改图书信息")
    public Result save(@RequestBody Book book){//有一样的参数就注解请求体 form少一个密码
        if(book.getId()==null){
            bookService.add(book);
        }else{
            bookService.update(book);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    @AutoLog("删除图书信息")
    public Result delete(@PathVariable Integer id){//由路径上接收的id

        bookService.delete(id);
        return Result.success();
    }

    @GetMapping("/echarts/bie")
    public Result bie(){
        //查询所有图书
        List<Book> list=bookService.findAll();
        Map<String,Long> collect=list.stream()//先弄成流
                .filter(x-> ObjectUtil.isNotEmpty(x.getTypeName()))
                .collect(Collectors.groupingBy(Book::getTypeName,Collectors.counting()));//过滤掉typename为空的再根据typename进行统计
        //最后返回给前端的数据结构
        List<Map<String,Object>> mapList=new ArrayList<>();
        if(CollectionUtil.isNotEmpty(collect)){
            for(String key: collect.keySet()){
                Map<String,Object> map=new HashMap<>();
                map.put("name",key);
                map.put("value",collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    @GetMapping("/echarts/bar")
    public Result bar(){
        List<Admin> list=adminService.findAll();
        Map<String,Long> collect=list.stream()//先弄成流
                .filter(x-> ObjectUtil.isNotEmpty(x.getRole()))
                .collect(Collectors.groupingBy(Admin::getRole,Collectors.counting()));

        List<String> xAxis=new ArrayList<>();
        List<Long> yAxis=new ArrayList<>();
        if(CollectionUtil.isNotEmpty(collect)){
            for(String key:collect.keySet()){
                xAxis.add(key);
                yAxis.add(collect.get(key));
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("xAxis",xAxis);
        map.put("yAxis",yAxis);
        return Result.success(map);
    }

    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<Book> list){
        for(Book book:list){//遍历删除
            bookService.delete(book.getId());
        }
        return Result.success();
    }

    @GetMapping("/lend")
    public Result lend(){
        List<Lend> list=lendService.findAll();
        return Result.success(list);
    }

}
