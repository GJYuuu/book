package com.example.controller;


import cn.hutool.core.date.DateUtil;
import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.dao.AdminDao;
import com.example.dao.BookDao;
import com.example.entity.Admin;
import com.example.entity.Book;
import com.example.entity.Params;
import com.example.entity.Reserve;
import com.example.service.AdminService;
import com.example.service.BookService;
import com.example.service.ReserveService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Resource
    private ReserveService reserveService;
    @Resource
    private BookDao bookDao;


    @GetMapping("/search")
    public Result findBySearch(Params params){//要跟前端的参数名称一样
        PageInfo<Reserve> info=reserveService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLog("图书借阅")
    public Result save(@RequestBody Reserve reserve){//有一样的参数就注解请求体 form少一个密码
        //判断剩余书是否为0
        Book book=bookDao.selectByPrimaryKey(reserve.getBookId());
        if(book.getNum()==0){
            return Result.error("剩余书数量不足");
        }

        List<Reserve> userlist=reserveService.findByUserId(reserve.getUserId());
        int num=userlist.size();
        Admin user=reserveService.adminAudit(reserve.getUserId());
        if(num>=user.getAdminnum()){
            return Result.error("可借阅书籍已达上限");
        }else{
            reserve.setTime(DateUtil.now());
            reserve.setState("借阅中");
            Date now = new Date();
            Date tomorrow = DateUtil.offsetDay(now, user.getAdminday());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String retime=formatter.format(tomorrow);
            reserve.setRetime(retime);
            reserve.setLate(0);
            reserve.setBack("借阅中");
            reserveService.add(reserve);

            book.setNum(book.getNum()-1);
            bookDao.updateByPrimaryKeySelective(book);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){//由路径上接收的id

        reserveService.delete(id);
        return Result.success();
    }

    @AutoLog("图书归还")
    @PostMapping("/back")
    public Result back(@RequestBody Reserve reserve){
        reserveService.back(reserve);
        return Result.success();
    }

    @GetMapping("/late")
    public Result late(){
        reserveService.late();
        return Result.success();
    }

    @GetMapping("/audit")
    public Result audit(@RequestParam Integer lendId,@RequestParam String state){
        reserveService.audit(lendId,state);
        return Result.success();
    }

    @GetMapping("/find")
    public Result find(Params params){
        PageInfo<Reserve> info=reserveService.find(params);
        return Result.success(info);
    }
}
