package com.example.dao;


import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Reserve;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ReserveDao extends Mapper<Reserve> {


    List<Reserve> findBySearch(@Param("params") Params params);

    @Select("select * from reserve where userId=#{userId} and state='借阅中'")
    List<Reserve> findByUserId(@Param("userId") Integer userId);

    @Select("select admin.*,lend.day as adminday,lend.num as adminnum from admin left join lend on lend.role=admin.role where admin.id=#{userId}")
    Admin adminAudit(@Param("userId") Integer userId);

    List<Reserve> find(@Param("params") Params params);
}
