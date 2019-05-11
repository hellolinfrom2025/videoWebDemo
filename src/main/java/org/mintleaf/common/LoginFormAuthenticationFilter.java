package org.mintleaf.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author by 15919
 * @createDate 2019/5/10 22:23
 */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String user = SecurityUtils.getSubject().getPrincipal().toString();
        if (user != null) {
            HttpServletRequest req = (HttpServletRequest) request;
            String loginType = (String) req.getParameter("loginType");
            // 正常情况下跳转的页面(后台管理页面)
            if ("background".equals(loginType)) {
                return super.onLoginSuccess(token, subject, request, response);
            } else if ("front".equals(loginType)) {
                // 清除登录前请求路径
                WebUtils.getAndClearSavedRequest(request);
                req.getSession().setAttribute("loginType", "front");
                //跳转到网站前台页面
                String fallbackUrl = "/videoWebFront/index.html";
                WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
            }
        }
        return false;
    }
}


