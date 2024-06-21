package com.example.service;



import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.dao.BookDao;
import com.example.dao.PrivateDao;
import com.example.dao.ReserveDao;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ReserveService {
    @Resource
    private ReserveDao reserveDao;
    @Resource
    private BookDao bookDao;
    @Resource
    private AdminDao adminDao;

    @Resource
    private PrivateDao privateDao;

    public PageInfo<Reserve> findBySearch(Params params) {
        Admin cuser= JwtTokenUtils.getCurrentUser();
        if(ObjectUtil.isEmpty(cuser)){
            throw new CustomException("从token中未解析到用户信息，请重新登录");
        }
        if(!cuser.getRole().equals("管理员")){
            params.setUserId(cuser.getId());
        }
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开的分页设置来查询
        List<Reserve> list= reserveDao.findBySearch(params);
        if(CollectionUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        for(Reserve reserve:list){
            if(ObjectUtil.isNotEmpty(reserve.getBookId())){
                Book book=bookDao.selectByPrimaryKey(reserve.getBookId());
                if(ObjectUtil.isNotEmpty(book)){
                    reserve.setBookName(book.getName());
                }
            }
            if(ObjectUtil.isNotEmpty(reserve.getUserId())){
                Admin admin=adminDao.selectByPrimaryKey(reserve.getUserId());
                if(ObjectUtil.isNotEmpty(admin)){
                    reserve.setUserName(admin.getName());
                }
            }
        }
        //除了裸数据，还包含了分页信息
        return PageInfo.of(list);
    }

    public void add(Reserve reserve) {
        reserveDao.insertSelective(reserve);
    }

    public void update(Reserve reserve) {
        reserveDao.updateByPrimaryKeySelective(reserve);
    }

    public void delete(Integer id) {
        reserveDao.deleteByPrimaryKey(id);
    }

    public List<Reserve> findByUserId(Integer userId) {
        return reserveDao.findByUserId(userId);
    }

    public Admin adminAudit(Integer userId) {
        return reserveDao.adminAudit(userId);
    }


    public void back(Reserve reserve) {
        Reserve data=reserveDao.selectByPrimaryKey(reserve.getId());
        data.setState("归还审核中");
        reserveDao.updateByPrimaryKeySelective(data);
    }

    public void late() {
        List<Reserve> list=reserveDao.selectAll();
        //更改逾期日期
        //获取时间戳,计算两个日期的时间戳的差，再除当天的毫秒数即可得到相差的天数。
        //今天的日期-归还日期
        Date now = new Date();
        String NOW=DateUtil.now();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Reserve reserve:list){
            if(reserve.getState().equals("借阅中")){
                Long starTime=now.getTime();
                Date end=null;
                try{
                    end=df.parse(reserve.getRetime());
                }catch (ParseException e){
                    e.printStackTrace();
                }
                if(end!=null){
                    Long endTime=end.getTime();
                    Long num=starTime-endTime;
                    if(num>0){
                        int late=(int)(num/24/60/60/1000);
                        reserve.setLate(late);
                        reserveDao.updateByPrimaryKeySelective(reserve);
                    }
                }
            }
        }
    }

    public void audit(Integer lendId, String state) {
        Reserve reserve=reserveDao.selectByPrimaryKey(lendId);
        if(state.equals("归还不通过")){
            reserve.setState("归还不通过");
            reserveDao.updateByPrimaryKeySelective(reserve);
        }else{
            reserve.setState("已归还");
            reserve.setBack(DateUtil.now());
            Book book=bookDao.selectByPrimaryKey(reserve.getBookId());
            int num= book.getNum();
            book.setNum(num+1);
            reserveDao.updateByPrimaryKeySelective(reserve);
            bookDao.updateByPrimaryKeySelective(book);
        }
    }


    public PageInfo<Reserve> find(Params params) {
        Admin cuser= JwtTokenUtils.getCurrentUser();
        if(ObjectUtil.isEmpty(cuser)){
            throw new CustomException("从token中未解析到用户信息，请重新登录");
        }
        if(!cuser.getRole().equals("管理员")){
            params.setUserId(cuser.getId());
        }
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开的分页设置来查询
        List<Reserve> list= reserveDao.find(params);
        if(CollectionUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        for(Reserve reserve:list){
            if(ObjectUtil.isNotEmpty(reserve.getBookId())){
                Book book=bookDao.selectByPrimaryKey(reserve.getBookId());
                if(ObjectUtil.isNotEmpty(book)){
                    reserve.setBookName(book.getName());
                }
            }
            if(ObjectUtil.isNotEmpty(reserve.getUserId())){
                Admin admin=adminDao.selectByPrimaryKey(reserve.getUserId());
                if(ObjectUtil.isNotEmpty(admin)){
                    reserve.setUserName(admin.getName());
                }
            }
        }
        //除了裸数据，还包含了分页信息
        return PageInfo.of(list);
    }
}
