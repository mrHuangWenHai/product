package com.mall.infra.persistence.sql.mapper;

import com.mall.domain.model.FeedbackModel;
import com.mall.domain.model.ProductModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    @Insert("insert into feedback(" +
            "status, " +
            "comment, " +
            "user_id, " +
            "create_time, " +
            "update_time" +
            ") values(" +
            "#{status}, " +
            "#{comment}, " +
            "#{userId}, " +
            "#{createTime}, " +
            "#{updateTime}" +
            ")")
    boolean insertFeedback(FeedbackModel feedbackModel);

    @Select("select * from feedback where status = #{status} order by create_time asc limit #{start}, #{size}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "status", column = "status"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<FeedbackModel> queryFeedback(@Param("status") int status, @Param("start") Long start, @Param("size") Byte size);

    @Delete("delete from feedback where id = #{id}")
    Long deleteFeedback(Long id);
}
