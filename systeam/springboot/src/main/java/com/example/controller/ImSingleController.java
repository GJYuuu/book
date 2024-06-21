package com.example.controller;

import cn.hutool.core.lang.Dict;
import com.example.common.Result;
import com.example.entity.ImSingle;
import com.example.service.ImSingleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/imsingle")
public class ImSingleController {
    @Resource
    private ImSingleService imSingleService;

    @GetMapping
    public Result findByFromUsername(@RequestParam String fromUser,@RequestParam String toUser){
        List<ImSingle> all=imSingleService.findByUsername(fromUser,toUser);
        return Result.success(all);
    }

    //查询未读消息
    @GetMapping("/unReadNums")
    public Result findUnReadNums(@RequestParam String toUsername){
        Dict dict=imSingleService.findByUnReadNums(toUsername);
        return Result.success(dict);
    }

}
