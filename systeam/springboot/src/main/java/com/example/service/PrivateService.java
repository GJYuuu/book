package com.example.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.dao.NoticeDao;
import com.example.dao.PrivateDao;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class PrivateService {
    @Resource
    private PrivateDao privateDao;

    @Resource
    private AdminDao adminDao;

    public PageInfo<Private> findBySearch(Params params){
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
        List<Private> list= privateDao.findBySearch(params);
        if(CollectionUtil.isEmpty(list)){
            return PageInfo.of(new ArrayList<>());
        }
        for(Private data:list){
            if(ObjectUtil.isNotEmpty(data.getUserId())){
                Admin admin=adminDao.selectByPrimaryKey(data.getUserId());
                if(ObjectUtil.isNotEmpty(admin)){
                    data.setUserName(admin.getName());
                }
            }
        }
        //除了裸数据，还包含了分页信息
        return PageInfo.of(list);
    }

    public void add(Private data) {
        Admin admin=adminDao.findByName(data.getUserName());
        data.setUserId(admin.getId());
        data.setTime(DateUtil.now());
        privateDao.insertSelective(data);
    }
}
