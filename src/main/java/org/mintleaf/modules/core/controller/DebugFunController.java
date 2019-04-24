package org.mintleaf.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 调试功能
 * @author: 林清流
 * @modified By：
 * @create: 2019-04-13 16:20
 */
@Api(tags = "调试功能控制器", description = "描述")
@Controller
@RequestMapping("debugFun")
public class DebugFunController {

    /**
     * 进入ckplayer页面
     * @return
     */
    @ApiOperation(value="超酷播放器", notes="描述")
    @RequestMapping(value = "ckPlayer.html",method = {RequestMethod.GET})
    public ModelAndView ckPlayer(){
        ModelAndView view = new ModelAndView("modules/debugfun/ckplayer/index.html");
        return view;
    }
    @RequestMapping(value = "ckPlayerH5.html",method = {RequestMethod.GET})
    public ModelAndView ckPlayerH5(){
        ModelAndView view = new ModelAndView("modules/debugfun/ckplayer/sample/h5.html");
        return view;
    }

    /**
     * 树形数据表格
     * @return
     */
    @ApiOperation(value="树形数据表格", notes="描述")
    @RequestMapping(value="treegrid.html",method = {RequestMethod.GET})
    public ModelAndView treegrid(){
        ModelAndView view =new ModelAndView("modules/debugfun/treegrid.html");
        return view;
    }

    /**
     * 树形下拉选择器
     * @return
     */
    @ApiOperation(value="树形下拉选择器", notes="描述")
    @RequestMapping(value="treeselect.html",method = {RequestMethod.GET})
    public ModelAndView treeselect(){
        ModelAndView view =new ModelAndView("modules/debugfun/treeselect.html");
        return view;
    }
}