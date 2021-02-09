package com.mall.api.request;

import javax.validation.constraints.NotNull;

public class UserRequest {
    @NotNull(message = "用户ID不能为空")
    private Integer wechatId;

    private String phoneNumber;
    private String avatorLink;
    private String name;

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
