package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.videowebfront.dao.OperationRecordDao;
import org.mintleaf.modules.videowebfront.entity.OperationRecord;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 前台用户管理主控制器
 *
 * @Author: linql
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags = "前台用户管理主控制器", description = "描述")
@Controller
@RequestMapping("videoWebFront/user")
public class UserController {

    @Autowired
    OperationRecordDao operationRecordDao;

    /**
     * 进入用户中心页面
     *
     * @return
     */
    @ApiOperation(value = "进入用户页面", notes = "描述")
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("modules/videowebfront/user/index.html");
    }

    /**
     * 进入用户基本设置页面
     *
     * @return
     */
    @ApiOperation(value = "进入用户基本设置页面", notes = "描述")
    @RequestMapping(value = "set.html", method = {RequestMethod.GET})
    public ModelAndView set() {
        return new ModelAndView("modules/videowebfront/user/set.html");
    }
    /**
     * 进入用户我的消息页面
     *
     * @return
     */
    @ApiOperation(value = "进入用户我的消息页面", notes = "描述")
    @RequestMapping(value = "message.html", method = {RequestMethod.GET})
    public ModelAndView message() {
        return new ModelAndView("modules/videowebfront/user/message.html");
    }

    /**
     * 获取用户收藏的视频
     *
     * @return
     */
    @ApiOperation(value = "获取用户收藏的视频", notes = "描述")
    @RequestMapping(value = "getCollect.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getCollect() {
        //获得当前登录的用户
        CoreUser user = (CoreUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        int uid = user.getId();
        List<OperationRecord> vs = operationRecordDao.getCollectVidByUId(uid);
        for (OperationRecord v : vs) {
            v.setVideoTitle(v.getTails().get("title").toString());
        }
        ResultMsg result = new ResultMsg();
        result.setData(vs);
        return result;
    }



}
