package com.example.dao;

import com.example.entity.Book;
import com.example.entity.Lend;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LendDao extends Mapper<Lend> {


}
