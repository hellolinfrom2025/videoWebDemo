package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.mintleaf.modules.core.dao.CoreMenuDao;
import org.mintleaf.modules.core.dao.CoreRoleDao;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.dao.CoreUserRoleDao;
import org.mintleaf.modules.core.entity.CoreRole;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.core.entity.CoreUserRole;
import org.mintleaf.modules.video.entity.Video;
import org.mintleaf.modules.video.entity.VideoTag;
import org.mintleaf.modules.videowebfront.dao.RecommendedDao;
import org.mintleaf.utils.MD5Util;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mintleaf.vo.ResultMsg.fail;
import static org.mintleaf.vo.ResultMsg.ok;

/**
 * 前台管理主控制器
 * @Author: MengchuZhang
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags="前台管理主控制器",description="描述")
@Controller
@RequestMapping("videoWebFront/index")
public class FrontIndexController {

    @Autowired
    CoreUserDao userDao;
    @Autowired
    CoreUserRoleDao userRoleDao;
    @Autowired
    CoreRoleDao roleDao;
    @Autowired
    CoreMenuDao menuDao;
    @Autowired
    RecommendedDao recommendedDao;
    /**
     * 进入前台首页
     * @return
     */
    @ApiOperation(value="进入前台首页", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view =new ModelAndView("modules/videowebfront/index.html");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.print(username);
        CoreUser user=userDao.sample(username);
        view.addObject("user", user);
        return view;
    }

    /**
     * 进入前台首页面
     * @return
     */
    @ApiOperation(value="进入前台首页面", notes="描述")
    @RequestMapping(value="home.html",method = {RequestMethod.GET})
    public ModelAndView home(){
        ModelAndView view =new ModelAndView("modules/videowebfront/home.html");
        return view;
    }

    /**
     * 登出操作
     * @return
     */
    @ApiOperation(value="登出操作", notes="描述")
    @RequestMapping(value = "loginOut.do", method ={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView loginOut() {
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/videoWebFront/index.html");
        SecurityUtils.getSubject().logout();
        return view;
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @ApiOperation(value="注册用户", notes="描述")
    @RequestMapping(value = "singUp.do",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg singUp(CoreUser user) {
        //向用户表插入新添加用户信息
        user.setPsw(MD5Util.MD5(user.getPsw()));
        user.setCreator(1);
        userDao.insertTemplate(user);
        //根据添加的用户名查出单条数据
        CoreUser newUser=userDao.sample(user.getName());
        //向用户角色关系表插入名为普通用户的角色
        List<CoreRole> roles = roleDao.sample("普通用户");
        for (CoreRole role : roles) {
            CoreUserRole ur = new CoreUserRole();
            ur.setUserid(newUser.getId());
            ur.setRoleid( Integer.valueOf(role.getId()));
            userRoleDao.insertTemplate(ur);
        }
        ResultMsg result=new ResultMsg();
        result.setData(user);
        result.setMsg("注册成功");
        return result;
    }

    /**
     * 忘记用户密码操作
     * @param user
     * @return
     */
    @ApiOperation(value="忘记用户密码操作", notes="描述")
    @RequestMapping(value = "/forgPass.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg forgPass(CoreUser user) {
        if (user.getPsw() == null || user.getPsw().length() <= 0 ) {
            return  fail("密码都不能为空");
        }
        CoreUser u = new CoreUser();
        u.setEmail(user.getEmail());
        CoreUser one = userDao.templateOne(u);
        if (one == null) {
            return  fail("邮箱不存在！");
        }else {
            one.setPsw(MD5Util.MD5(user.getPsw()));
            userDao.updateTemplateById(one);
        }
        return ok();
    }

    /**
     * 获得最近一周的视频播放量的前六
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得最近一周的视频播放量的前六", notes = "描述")
    @RequestMapping(value = "getHotVideo.do", method = {RequestMethod.GET})
    public ResultMsg getVideoCurrHot() {
        List<VideoTag> videos = recommendedDao.getVideoCurrHot("1");
        List<Video> list = new ArrayList<>();
        for (VideoTag video : videos) {
            Video v = new Video();
            v.setTitle(video.getTails().get("title").toString());
            v.setCover(video.getTails().get("cover").toString());
            v.setId(video.getVideoId());
            v.setPlayCount((Integer.valueOf(video.getTails().get("value").toString())));
            list.add(v);
        }
        ResultMsg result = new ResultMsg();
        result.setData(list);
        return result;
    }

    /**
     * 获得最新上传的视频的前六
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获得最新上传的视频的前六", notes = "描述")
    @RequestMapping(value = "getNewUploadVideo.do", method = {RequestMethod.GET})
    public ResultMsg getNewUploadVideo() {
        List<Video> videos = recommendedDao.getNewUploadVideo();
        List<Video> list = new ArrayList<>();
        for (Video video : videos) {
            Video v = new Video();
            v.setTitle(video.getTitle());
            v.setCover(video.getCover());
            v.setId(video.getId());
            v.setPlayCount(video.getPlayCount());
            list.add(v);
        }
        ResultMsg result = new ResultMsg();
        result.setData(list);
        return result;
    }
}
