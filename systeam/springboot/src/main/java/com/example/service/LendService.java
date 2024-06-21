package com.example.service;


import cn.hutool.core.date.DateUtil;
import com.example.dao.LendDao;
import com.example.dao.NoticeDao;
import com.example.entity.Lend;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LendService {

    @Resource
    private LendDao lendDao;
    public List<Lend> findAll() {
        return lendDao.selectAll();
    }
}
