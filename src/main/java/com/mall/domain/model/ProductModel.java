package com.mall.domain.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ProductModel {
    private int id;
    private Timestamp createTime;
    private Timestamp updateTime;

    @NotBlank(message = "产品名称不能为空")
    private String name;

    @NotBlank(message = "产品链接不能为空")
    private String link;

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp create_time) {
        this.createTime = create_time;
    }

    public Timestamp getUpdate_tiem() {
        return updateTime;
    }

    public void setUpdateTiem(Timestamp update_tiem) {
        this.updateTime = update_tiem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
