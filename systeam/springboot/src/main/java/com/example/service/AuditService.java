package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.dao.AuditDao;
import com.example.entity.Admin;
import com.example.entity.Audit;
import com.example.entity.Params;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AuditService {
    @Resource
    private AuditDao auditDao;
    @Resource
    private AdminDao adminDao;

    public void delete(Integer id) {
        auditDao.deleteByPrimaryKey(id);
    }

    public void add(Audit audit) {
        auditDao.insertSelective(audit);
    }

    public void update(Audit audit) {
        auditDao.updateByPrimaryKeySelective(audit);
    }

    public PageInfo<Audit> findBySearch(Params params) {
        Admin cuser=JwtTokenUtils.getCurrentUser();
        if(ObjectUtil.isEmpty(cuser)){
            throw new CustomException("从token中未解析到用户信息，请重新登录");
        }
        if(!cuser.getRole().equals("管理员")){
            params.setUserId(cuser.getId());
        }
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Audit> list=auditDao.findBySearch(params);
        for(Audit audit:list){
            if(ObjectUtil.isNotEmpty(audit.getUserId())){
                Admin user=adminDao.selectByPrimaryKey(audit.getUserId());
                if(ObjectUtil.isNotEmpty(user)){
                    audit.setUserName(user.getName());
                }
            }
        }
        return PageInfo.of(list);
    }

}
