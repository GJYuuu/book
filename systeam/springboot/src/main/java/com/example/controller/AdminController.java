package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.AutoLog;
import com.example.common.CaptureConfig;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Book;
import com.example.entity.Params;
import com.example.entity.Type;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log= LoggerFactory.getLogger(AdminController.class);
    @Resource
    private AdminService adminService;
    @GetMapping
    public Result findAll(){
        List<Admin> list =adminService.findAll();
        return Result.success(list);
    }
//所有的返回都是Result
    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Admin> info=adminService.findBySearch(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody Admin admin){//有一样的参数就注解请求体 form少一个密码
        if(admin.getId()==null){
            adminService.add(admin);
        }else{
            adminService.update(admin);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){//由路径上接收的id

        adminService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    @AutoLog("用户登录")
    public Result login(@RequestBody Admin admin, @RequestParam String key, HttpServletRequest request){
        //判断验证码
        if(!admin.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))){
            //清空验证码
            CaptchaUtil.clear(request);
            return Result.error("验证码不正确");
        }
        Admin loginUser=adminService.login(admin);
        CaptureConfig.CAPTURE_MAP.remove(key);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Admin admin){
        adminService.add(admin);//注册与新增同理
        return Result.success();
    }

    @GetMapping("/echarts/bar")
    public Result bar(){
        //查询所有图书
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

    @GetMapping("/alter/{id}")
    public Result findByName(@PathVariable String id){
        Admin admin=adminService.findId(Integer.parseInt(id));
        return Result.success(admin);
    }

    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<Admin> list){
        for(Admin admin:list){//遍历删除
            adminService.delete(admin.getId());
        }
        return Result.success();
    }

    @PostMapping("/pass")
    public Result pass(@RequestBody Admin admin){
        adminService.pass(admin);
        return Result.success();
    }
}
