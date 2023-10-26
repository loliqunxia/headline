package com.cc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 陈畅
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-14 22:17:59
* @Entity com.cc.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




