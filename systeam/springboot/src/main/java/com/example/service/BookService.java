package com.example.service;


import com.example.dao.BookDao;
import com.example.entity.Admin;
import com.example.entity.Book;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BookService {
    @Resource
    private BookDao bookDao;

    public PageInfo<Book> findBySearch(Params params) {
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开的分页设置来查询
        List<Book> list= bookDao.findBySearch(params);
        //除了裸数据，还包含了分页信息
        return PageInfo.of(list);
    }

    public void add(Book book) {
        bookDao.insertSelective(book);
    }

    public void update(Book book) {
        bookDao.updateByPrimaryKeySelective(book);
    }

    public void delete(Integer id) {
        bookDao.deleteByPrimaryKey(id);
    }

    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
