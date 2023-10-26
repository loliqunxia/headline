package com.cc.service;

import com.cc.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.utils.Result;

/**
* @author 陈畅
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-14 22:17:59
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
