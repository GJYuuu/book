package com.example.controller;

import com.example.common.Result;
import com.example.entity.Log;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @PostMapping
    public Result save(@RequestBody Log log){//有一样的参数就注解请求体 form少一个密码
        logService.add(log);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Log> info=logService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){//由路径上接收的id

        logService.delete(id);
        return Result.success();
    }

    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<Log> list){
        for(Log log:list){//遍历删除
            logService.delete(log.getId());
        }
        return Result.success();
    }
}
