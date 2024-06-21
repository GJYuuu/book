package com.example.dao;


import com.example.entity.ImSingle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface ImSingleDao extends Mapper<ImSingle> {


    @Select("select * from imsingle where (fromuser=#{fromUser} and touser=#{toUser}) or (fromuser=#{toUser} and touser=#{fromUser})")
    List<ImSingle> findByUsername(@Param("fromUser") String fromUser, @Param("toUser") String toUser);

    @Select("select * from imsingle where touser=#{toUsername} and readed=0")
    List<ImSingle> findByToUsername(@Param("toUsername") String toUsername);
}
