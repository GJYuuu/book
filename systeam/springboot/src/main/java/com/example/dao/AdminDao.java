package com.example.dao;

import com.example.entity.Admin;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdminDao extends Mapper<Admin> {

    List<Admin> findBySearch(@Param("params") Params params);//给参数注解(命个名，之后xml参数名称就是这个,可以任意命名，但最好跟后面的参数名称保持一致） 并去xml写sql语句

    @Select("select admin.*,lend.day as adminday,lend.num as adminnum from admin left join lend on admin.role=lend.role where name=#{name}")//有一个都不行
    Admin findByName(@Param("name") String name);

    @Select("select * from admin where name=#{name} and password=#{password} limit 1")
    Admin findByNameAndPassword(@Param("name") String name,@Param("password") String password);

    @Select("select admin.*,lend.day as adminday,lend.num as adminnum from admin left join lend on admin.role=lend.role where admin.id=#{id}")
    Admin findId(@Param("id") Integer id);
}
