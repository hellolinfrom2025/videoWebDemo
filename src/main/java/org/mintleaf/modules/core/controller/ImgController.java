package org.mintleaf.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 图片管理相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/9/14 9:17
 * @Version 1.0
 */
@Api(tags="图片管理相关控制器",description="描述")
@Controller
@RequestMapping("img")
public class ImgController {

    /**
     * 进入首页
     * @return
     */
    @ApiOperation(value="进入首页", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index(){
        ModelAndView view =new ModelAndView("modules/core/img/images.html");
        return view;
    }

}
