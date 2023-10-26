package com.cc.controller;

import com.cc.pojo.Headline;
import com.cc.pojo.vo.PortalVo;
import com.cc.service.HeadlineService;
import com.cc.service.TypeService;
import com.cc.utils.Result;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("portal")
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){

        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public  Result showHeadlineDetail(Integer hid){

        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
