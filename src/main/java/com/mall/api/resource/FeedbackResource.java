package com.mall.api.resource;

import com.mall.api.request.FeedbackRequest;
import com.mall.api.response.Response;
import com.mall.api.response.Responses;
import com.mall.api.utils.StringUtil;
import com.mall.domain.model.FeedbackModel;
import com.mall.domain.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(value = "feedbacks")
public class FeedbackResource {
    private final Logger logger = LoggerFactory.getLogger(FeedbackResource.class);

    @Resource
    private FeedbackService feedbackService;

    @PostMapping(value = "")
    public Response addFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加评论失败, 验证错误!");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            FeedbackModel feedbackModel = new FeedbackModel();
            feedbackModel.setStatus(0);
            feedbackModel.setComment(feedbackRequest.getComment());
            feedbackModel.setUserId(feedbackRequest.getUserId());
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            feedbackModel.setCreateTime(currentTime);
            feedbackModel.setUpdateTime(currentTime);

            int feedbackId = feedbackService.addFeedback(feedbackModel);
            if (feedbackId <= 0) {
                return Responses.errorResponse("添加反馈失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("feedbackId", feedbackId);
            response.setData(data);
            return response;
        }
    }

    @GetMapping(value = "")
    public Response feedbackList(
            @RequestParam(value = "status", defaultValue = "0") String status,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size) {
        int statusValue = StringUtil.stringToInt(status);
        Long pageValue = StringUtil.stringToLong(page);
        Byte sizeValue = StringUtil.stringToByte(size);
        List<FeedbackModel> feedbackModels = feedbackService.listFeedback(statusValue, pageValue, sizeValue);

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("products", feedbackModels);
        data.put("size", feedbackModels.size());
        response.setData(data);
        return response;
    }

    @DeleteMapping("/{id}")
    public Response deleteFeedback(@PathVariable("id") String id) {
        logger.info("invoke deleteFeedback{}, id={id}", id);
        Long feedbackId = StringUtil.stringToLong(id);
        Long deleteId = feedbackService.deleteFeedback(feedbackId);
        if (deleteId < 0) {
            return Responses.errorResponse("删除反馈失败");
        }
        return Responses.successResponse();
    }
}
