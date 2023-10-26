package com.cc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.pojo.Type;
import com.cc.service.TypeService;
import com.cc.mapper.TypeMapper;
import com.cc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈畅
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-10-14 22:17:59
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Result findAllTypes() {

        List<Type> types = typeMapper.selectList(null);

        return Result.ok(types);
    }
}




