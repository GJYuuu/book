package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Book;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @AutoLog("更新系统公告")
    @PostMapping
    public Result update(@RequestBody Notice notice){
        if(ObjectUtil.isEmpty(notice.getId())){
            noticeService.add(notice);
        }else{
            noticeService.update(notice);
        }
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Notice> info=noticeService.findBySearch(params);
        return Result.success(info);
    }

    @AutoLog("删除系统公告")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        noticeService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result findTop(){
        List<Notice> list=noticeService.findTop();
        return Result.success(list);
    }

    @PutMapping("/delBatch")
    public Result delBatch(@RequestBody List<Notice> list){
        for(Notice notice:list){//遍历删除
            noticeService.delete(notice.getId());
        }
        return Result.success();
    }
}
