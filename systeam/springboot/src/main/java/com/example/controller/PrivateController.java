package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Private;
import com.example.service.NoticeService;
import com.example.service.PrivateService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/private")
public class PrivateController {
    @Resource
    private PrivateService privateService;

    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Private> info=privateService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result update(@RequestBody Private data){
        privateService.add(data);
        return Result.success();
    }


}
