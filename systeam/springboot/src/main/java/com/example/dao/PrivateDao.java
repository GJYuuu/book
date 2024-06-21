package com.example.dao;


import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Private;
import com.example.entity.Reserve;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface PrivateDao extends Mapper<Private> {

    List<Private> findBySearch(@Param("params") Params params);

}
