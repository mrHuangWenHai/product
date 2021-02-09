package com.mall.infra.persistence.sql.mapper;

import com.mall.domain.model.UserModel;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserMapper {

    @Insert("insert into user(" +
            "name, " +
            "phone_number, " +
            "wechat_id, " +
            "avator_link, " +
            "create_time, " +
            "update_time" +
            ") values(" +
            "#{name}, " +
            "#{phoneNumber}, " +
            "#{wechatId}, " +
            "#{avatorLink}, " +
            "#{createTime}, " +
            "#{updateTime}" +
            ")")
    Long insertUser(UserModel userModel);

    @Select("select * from user where id = #{userId}")
    @Results ({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "avatorLink", column = "avator_link"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    UserModel getUser(Integer userId);
}
