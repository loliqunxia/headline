package com.cc.service;

import com.cc.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.pojo.vo.PortalVo;
import com.cc.utils.Result;

/**
* @author 陈畅
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-14 22:17:59
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline,String token);

    Result updateDate(Headline headline);
}
