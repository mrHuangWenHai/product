package com.mall.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FeedbackRequest {
    @NotNull(message = "用户id不能为空")
    Integer userId;

    @NotBlank(message = "用户评论不能为空")
    String comment;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
