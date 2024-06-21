package com.example.controller;


import com.example.common.Result;
import com.example.entity.Audit;
import com.example.entity.Params;
import com.example.service.AuditService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



@CrossOrigin
@RestController
@RequestMapping("/audit")
public class AuditController {
    @Resource
    private AuditService auditService;

    @PostMapping
    public Result save(@RequestBody Audit audit){//有一样的参数就注解请求体 form少一个密码
        if(audit.getId()==null){
            auditService.add(audit);
        }else{
            auditService.update(audit);
        }
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Audit> info=auditService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){//由路径上接收的id

        auditService.delete(id);
        return Result.success();
    }
}
