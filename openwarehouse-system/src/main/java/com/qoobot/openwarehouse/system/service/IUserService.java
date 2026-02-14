package com.qoobot.openwarehouse.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qoobot.openwarehouse.system.entity.User;

/**
 * 用户服务接口
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     */
    User getUserByUsername(String username);

    /**
     * 用户登录
     */
    String login(String username, String password);

    /**
     * 用户登出
     */
    void logout(String token);
}
