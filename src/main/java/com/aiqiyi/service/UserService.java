package com.aiqiyi.service;

import com.aiqiyi.entity.User;
import com.aiqiyi.utils.Result;

public interface UserService {
    /**
     * 用户注册
     * @param user 用户对象
     * @return
     */
    Result regist(User user,String surePassword);
}
