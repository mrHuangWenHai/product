package com.mall.domain.service;

import com.mall.domain.model.UserModel;
import com.mall.infra.persistence.sql.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserService {


    @Resource
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param userModel
     * @return
     */
    public Long addUser(UserModel userModel) {
        return  userMapper.insertUser(userModel);
    }

    public UserModel queryUser(Integer userId) {
        return userMapper.getUser(userId);
    }
}
