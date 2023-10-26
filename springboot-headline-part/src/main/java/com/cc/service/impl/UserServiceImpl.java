package com.cc.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.pojo.User;
import com.cc.service.UserService;
import com.cc.mapper.UserMapper;
import com.cc.utils.JwtHelper;
import com.cc.utils.MD5Util;
import com.cc.utils.Result;
import com.cc.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 陈畅
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-10-14 22:17:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     *
     *
     *
     */
    @Override
    public Result login(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper =  new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());

        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        if (loginUser == null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if (!StringUtils.isEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){

            //登录成功
            //根据id生产token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            //将token封装到result返回
            Map data = new HashMap();
            data.put("token",token);
            return Result.ok(data);

        }
        //密码错误
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    /**
     * 根据token获取用户数据
     *
     *
     *
     */
    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration){
            //失效,未登录看待
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();

        User user = userMapper.selectById(userId);
        user.setUserPwd("");

        Map data = new HashMap();
        data.put("loginUser",user);

        return Result.ok(data);
    }

    /**
     * 检查账号是否可用
     *
     * @param username
     * @return
     */

    @Override
    public Result checkUserName(String username) {

        LambdaQueryWrapper<User> queryWrapper
                = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getUsername,username);
        Long count = userMapper.selectCount(queryWrapper);

        if (count == 0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 注册业务
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {

        LambdaQueryWrapper<User> queryWrapper
                = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);
    }
}




