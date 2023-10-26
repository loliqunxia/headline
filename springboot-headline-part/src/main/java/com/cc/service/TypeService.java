package com.cc.service;

import com.cc.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.utils.Result;

/**
* @author 陈畅
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-10-14 22:17:59
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
