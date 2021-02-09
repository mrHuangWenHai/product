package com.mall.domain.model;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class UserModel {
    private int id;
    private Timestamp create_time;
    private Timestamp update_tiem;

    @NotNull(message = "用户ID不能为空")
    private Integer wechatId;

    private String phoneNumber;
    private String avatorLink;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_tiem() {
        return update_tiem;
    }

    public void setUpdate_tiem(Timestamp update_tiem) {
        this.update_tiem = update_tiem;
    }

    public Integer getWechatId() {
        return wechatId;
    }

    public void setWechatId(Integer wechatId) {
        this.wechatId = wechatId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatorLink() {
        return avatorLink;
    }

    public void setAvatorLink(String avatorLink) {
        this.avatorLink = avatorLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
