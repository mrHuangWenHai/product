package com.mall.infra.persistence.sql.mapper;

import com.mall.domain.model.ProductModel;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     *
     * @param start
     * @param size
     * @return list productModel
     */
    @Select("select * from product where user_id = #{userId} order by create_time asc limit #{start}, #{size}")
    @Results ({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "link", column = "link"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<ProductModel> queryAgent(@Param("userId") int userId,@Param("start") Long start, @Param("size") Byte size);

    @Insert("insert into product(" +
            "name, " +
            "link, " +
            "user_id, " +
            "create_time, " +
            "update_time" +
            ") values(" +
            "#{name}, " +
            "#{link}, " +
            "#{userId}, " +
            "#{createTime}, " +
            "#{updateTime}" +
            ")")
    boolean insertProduct(ProductModel productModel);

    @Delete("delete from product where id = #{id}")
    Long deleteProduct(Long id);
}
