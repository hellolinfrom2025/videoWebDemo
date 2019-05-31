package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.mintleaf.modules.core.dao.CoreMenuDao;
import org.mintleaf.modules.core.dao.CoreRoleDao;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.modules.core.dao.CoreUserRoleDao;
import org.mintleaf.modules.core.entity.CoreRole;
import org.mintleaf.modules.core.entity.CoreUser;
import org.mintleaf.modules.core.entity.CoreUserRole;
import org.mintleaf.modules.video.dao.VideoTypeDao;
import org.mintleaf.modules.video.entity.VideoType;
import org.mintleaf.modules.videowebfront.dao.FrontVideoDao;
import org.mintleaf.modules.videowebfront.dao.RecommendedDao;
import org.mintleaf.utils.MD5Util;
import org.mintleaf.vo.PageFrame;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.mintleaf.vo.ResultMsg.fail;
import static org.mintleaf.vo.ResultMsg.ok;

/**
 * 前台管理主控制器
 *
 * @Author: linql
 * @Date: 2018/8/15 8:24
 * @Version 1.0
 */
@Api(tags = "前台管理主控制器", description = "描述")
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
    @Autowired
    FrontVideoDao frontVideoDao;
    @Autowired
    VideoTypeDao typeDao;

    /**
     * 进入前台首页
     *
     * @return
     */
    @ApiOperation(value = "进入前台首页", notes = "描述")
    @RequestMapping(value = "index.html", method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("modules/videowebfront/index.html");
    }

    /**
     * 进入前台分类搜索页面
     * order 0:最新、1：最热
     * @return
     */
    @ApiOperation(value = "进入前台分类搜索页面", notes = "描述")
    @RequestMapping(value = "category.html", method = {RequestMethod.GET})
    public ModelAndView category(Integer pageNo, Integer pageSize, String type,
                                 String country, String year, Integer order) {
        if (pageSize == null) {
            pageSize = 30;
        }
        if (order == null) {
            order = 0;
        }
        List<VideoType> v1s = typeDao.findByNameAndPid(type, 2);
        String[] typeIds = new String[v1s.size()];
        for (int i = 0; i < v1s.size(); i++) {
            typeIds[i] = v1s.get(i).getId().toString();
        }
        List<VideoType> v2s = typeDao.findByNameAndPid(country, 3);
        String[] countryIds = new String[v2s.size()];
        for (int i = 0; i < v2s.size(); i++) {
            countryIds[i] = v2s.get(i).getId().toString();
        }
        List<VideoType> v3s = typeDao.findByNameAndPid(year, 1);
        String[] yearIds = new String[v3s.size()];
        for (int i = 0; i < v3s.size(); i++) {
            yearIds[i] = v3s.get(i).getId().toString();
        }

        PageQuery query = frontVideoDao.findVdoPageByCate(pageNo, pageSize, typeIds, countryIds, yearIds, order);
        PageFrame pageFrame = new PageFrame();
        pageFrame.setList(query.getList());
        pageFrame.setPageNum(Long.valueOf(pageNo));
        pageFrame.setPageSize(Long.valueOf(pageSize));
        pageFrame.setPages(query.getTotalPage());
        pageFrame.setTotal(query.getTotalRow());
        ModelAndView view = new ModelAndView();
        view.addObject("videoList", pageFrame);
        view.setViewName("modules/videowebfront/category.html");
        return view;
    }

    /**
     * 登出操作
     *
     * @return
     */
    @ApiOperation(value = "登出操作", notes = "描述")
    @RequestMapping(value = "loginOut.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginOut(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/videoWebFront/index/index.html");
        SecurityUtils.getSubject().logout();
        return view;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "注册用户", notes = "描述")
    @RequestMapping(value = "singUp.do", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg singUp(CoreUser user) {
        //向用户表插入新添加用户信息
        user.setPsw(MD5Util.MD5(user.getPsw()));
        user.setCreator(1);
        userDao.insertTemplate(user);
        //根据添加的用户名查出单条数据
        CoreUser newUser = userDao.sample(user.getName());
        //向用户角色关系表插入名为普通用户的角色
        List<CoreRole> roles = roleDao.sample("普通用户");
        for (CoreRole role : roles) {
            CoreUserRole ur = new CoreUserRole();
            ur.setUserid(newUser.getId());
            ur.setRoleid(Integer.valueOf(role.getId()));
            userRoleDao.insertTemplate(ur);
        }
        ResultMsg result = new ResultMsg();
        result.setData(user);
        result.setMsg("注册成功");
        return result;
    }

    /**
     * 忘记用户密码操作
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "忘记用户密码操作", notes = "描述")
    @RequestMapping(value = "/forgPass.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg forgPass(CoreUser user) {
        if (user.getPsw() == null || user.getPsw().length() <= 0) {
            return fail("密码都不能为空");
        }
        CoreUser u = new CoreUser();
        u.setEmail(user.getEmail());
        CoreUser one = userDao.templateOne(u);
        if (one == null) {
            return fail("邮箱不存在！");
        } else {
            one.setPsw(MD5Util.MD5(user.getPsw()));
            userDao.updateTemplateById(one);
        }
        return ok();
    }
}
