package org.mintleaf.modules.videowebfront.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.mintleaf.modules.core.dao.CoreMenuDao;
import org.mintleaf.modules.core.dao.CoreUserDao;
import org.mintleaf.utils.MD5Util;
import org.mintleaf.vo.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("videoWebFront")
public class FrontIndexController {

    @Autowired
    HttpSession session;

    @Autowired
    CoreUserDao coreUserDao;

    @Autowired
    CoreMenuDao coreMenuDao;

    /**
     * 进入前台首页
     * @return
     */
//  @RequiresRoles("超级用户")
    @ApiOperation(value="进入前台首页", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view =new ModelAndView("modules/videowebfront/index.html");
        return view;
    }

    /**
     * 进入登陆页面
     * @return
     */
    @ApiOperation(value="进入登陆页面", notes="描述")
    @RequestMapping(value="login.html",method = {RequestMethod.GET})
    public ModelAndView login(){
        ModelAndView view =new ModelAndView("login.html");
        return view;
    }

    /**
     * 进入前台首页面
     * @return
     */
    @ApiOperation(value="进入前台首页面", notes="描述")
    @RequestMapping(value="home.html",method = {RequestMethod.GET})
    public ModelAndView home(){
        ModelAndView view =new ModelAndView("home.html");
        return view;
    }

    /**
     * 登陆操作
     * @param request
     * @return
     */
    @ApiOperation(value="登陆操作", notes="描述")
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg loginDo(HttpServletRequest request){
        //验证码校验
//        if (!CaptchaUtils.checkVerifyCode(request)) {
//            return fail("验证码有误！");
//        }
        String name = request.getParameter("name");
        String password = request.getParameter("password");
//        CoreUser coreUser = new CoreUser();
//        coreUser.setUsercode(usercode);
////      coreUser.setPassword(password);
//        CoreUser end=coreUserDao.sample(coreUser.getUsercode());
//        if (end!=null) {
//            session.setAttribute("user", coreUser.getUsercode());
//            return ResultMsg.ok(url);
//        } else {
//            return ResultMsg.fail();
//        }
        //用户名密码校验
        UsernamePasswordToken token = new UsernamePasswordToken(name, MD5Util.MD5(password));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("用户名或密码错误！");
        }
        return ok();
    }

    /**
     * 登出操作
     * @return
     */
    @ApiOperation(value="登出操作", notes="描述")
    @RequestMapping(value = "loginOut.do", method ={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView loginOut() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/login.html");
        SecurityUtils.getSubject().logout();
        return view;
    }


}
