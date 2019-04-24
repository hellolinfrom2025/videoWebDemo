package org.mintleaf.common;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver{

    public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("==============异常开始=============");
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
        if(ex instanceof UnauthorizedException){
            ModelAndView mv = new ModelAndView("modules/debugfun/403.html");
            return mv;
        }
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView("modules/debugfun/403.html");
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
        return mv;
    }



}
