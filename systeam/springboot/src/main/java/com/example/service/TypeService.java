package com.example.service;


import com.example.dao.TypeDao;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Type;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TypeService {
    @Resource
    private TypeDao typeDao;

    public void delete(Integer id) {
        typeDao.deleteByPrimaryKey(id);
    }

    public void add(Type type) {
        typeDao.insertSelective(type);
    }

    public void update(Type type) {
        typeDao.updateByPrimaryKeySelective(type);
    }

    public PageInfo<Type> findBySearch(Params params) {
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开的分页设置来查询
        List<Type> list= typeDao.findBySearch(params);
        //除了裸数据，还包含了分页信息
        return PageInfo.of(list);
    }

    public List<Type> findAll() {
        return typeDao.selectAll();
    }
}
