package com.mall.domain.service;

import com.mall.domain.model.FeedbackModel;
import com.mall.infra.persistence.sql.mapper.FeedbackMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    /**
     * 查询反馈列表
     * @param status
     * @param start
     * @param size
     * @return
     */
    public List<FeedbackModel> listFeedback(int status,  Long start, Byte size) {
        return feedbackMapper.queryFeedback(status, start, size);
    }

    /**
     * 添加反馈
     * @param feedbackModel
     * @return
     */
    public int addFeedback(FeedbackModel feedbackModel) {
        int feedbackId = -1;
        if (feedbackMapper.insertFeedback(feedbackModel)) {
            feedbackId = feedbackModel.getId();
        }
        return feedbackId;
    }

    /**
     * 删除评论
     * @param feedbackId
     * @return
     */
    public Long deleteFeedback(Long feedbackId) {
        return feedbackMapper.deleteFeedback(feedbackId);
    }
}
