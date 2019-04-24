package org.mintleaf.modules.video.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mintleaf.modules.video.dao.VideoTypeDao;
import org.mintleaf.modules.video.entity.VideoType;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 角色相关控制器
 *
 * @Author: Lin
 * @Date: 2018/8/23 15:38
 * @Version 1.0
 */
@Api(tags = "视频分类标签控制器", description = "描述")
@Controller
@RequestMapping("videoType")
public class VideoTypeController {
    @Autowired
    VideoTypeDao videoTypeDao;

    /**
     * 进入列表页面
     *
     * @return
     */
    @ApiOperation(value = "进入列表页面", notes = "描述")
    @RequestMapping(value = "type.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("modules/video/videotype/index.html");
        return view;
    }

    /**
     * 获得视频类型
     *
     * @return lll
     */
    @ApiOperation(value = "获得视频类型", notes = "描述")
    @RequestMapping(value = "getVideoType.json", method = {RequestMethod.GET})
    @ResponseBody
    public ResultMsg getVideoType() {
        List<VideoType> videoTypes = videoTypeDao.all();
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setData(videoTypes);
        return resultMsg;
    }


    /**
     * 添加操作
     * @param videoType
     * @return
     */
    @ApiOperation(value="添加操作", notes="描述")
    @RequestMapping(value = "add.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(VideoType videoType) {
        //向用户表插入新添加用户信息
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        videoType.setCreatePerson(username);
        videoType.setId(new Random().nextInt());
        videoType.setCreateTime(new Date());
        videoTypeDao.insertTemplate(videoType);
        ResultMsg result=new ResultMsg();
        result.setData(videoType);
        return result;
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequiresPermissions("role:add")
    @ApiOperation(value = "进入新增页面", notes = "描述")
    @RequestMapping(value = "add.html", method = {RequestMethod.GET})
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("modules/video/videotype/add.html");
        return view;
    }

    /**
     * 删除操作
     * @param videoType
     * @return
     */
    @ApiOperation(value="删除操作", notes="描述")
    @RequestMapping(value = "delete.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(VideoType videoType) {
        //删除当前节点
        videoTypeDao.deleteById(videoType.getId());
        //若有子节点，则删除
        List<VideoType> childVideoTypes = videoTypeDao.findChildTypeByPid(videoType.getId());
        for (VideoType childVideoType : childVideoTypes) {
            videoTypeDao.deleteById(childVideoType.getId());
        }
        ResultMsg result=new ResultMsg();
        return result;
    }

    /**
     * 获得视频标签数据
     * @param videoType
     * @return
     */
    @ApiOperation(value="获得视频标签数据", notes="描述")
    @RequestMapping(value = "getTag.json",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg getTagByName(VideoType videoType) {
        List<VideoType> videoTypes = videoTypeDao.findChildTypeByPid(videoType.getId());
        ResultMsg result=new ResultMsg();
        result.setData(videoTypes);
        return result;
    }


}
