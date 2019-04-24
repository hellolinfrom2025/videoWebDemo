package org.mintleaf.modules.core.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mintleaf.utils.CaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * 验证码相关控制器
 * @Author: MengchuZhang
 * @Date: 2018/8/26 18:32
 * @Version 1.0
 */
@Api(tags="验证码相关控制器",description="描述")
@Controller
@RequestMapping("captcha")
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    /**
     * 获取验证码图片
     * @param request
     * @return
     */
    @ApiOperation(value="获取验证码图片", notes="描述")
    @RequestMapping(value="getCaptcha.jpg",method = {RequestMethod.GET})
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 测试验证码接口
     * @param request
     * @return
     */
    @ApiOperation(value="测试验证码接口", notes="描述")
    @RequestMapping("test.do")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        if (!CaptchaUtils.checkVerifyCode(request)) {
            return "验证码有误！";
        } else {
            return "验证码校验正确";
        }
    }

    /**
     * 测试验证码页面
     * @return
     */
    @ApiOperation(value="测试验证码页面", notes="描述")
    @RequestMapping(value="index.html",method = {RequestMethod.GET})
    public ModelAndView login(){
        ModelAndView view =new ModelAndView("captcha.html");
        return view;
    }
}
