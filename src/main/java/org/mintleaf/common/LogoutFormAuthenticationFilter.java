package org.mintleaf.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author by 15919
 * @createDate 2019/5/10 22:55
 */
public class LogoutFormAuthenticationFilter extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        HttpServletRequest req = (HttpServletRequest) request;
        SecurityUtils.getSubject().logout();
        //在前面登陆的过滤器中存储的数据，用来分辨是不是后台登陆
        String clientType =  req.getSession().getAttribute ("loginType").toString();
        subject.logout();
        // 请求方为后台退出，执行原方法
        if("background".equals (clientType) ) {
            issueRedirect(request, response, "/login.html");
        }else if ("front".equals (clientType) ){
            issueRedirect(request, response, "/videoWebFront/index.html");
        }
        return false;
    }
}


