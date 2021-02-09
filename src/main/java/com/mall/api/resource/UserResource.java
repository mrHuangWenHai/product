package com.mall.api.resource;

import com.mall.api.response.Response;
import com.mall.api.response.Responses;
import com.mall.api.utils.StringUtil;
import com.mall.domain.model.UserModel;
import com.mall.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "users")
public class UserResource {
    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Resource
    private UserService userService;

    @GetMapping(value = "{id}")
    public Response queryUser(@PathVariable("id") String id) {
        logger.info("invoke queryUser, url is users/{id} {}", id);

        UserModel userModel = userService.queryUser(StringUtil.stringToInt(id));
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", userModel);
        response.setData(data);
        return response;
    }

}
