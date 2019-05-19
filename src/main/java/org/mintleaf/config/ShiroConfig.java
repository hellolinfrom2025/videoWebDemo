package org.mintleaf.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mintleaf.common.MyExceptionResolver;
import org.mintleaf.common.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/13 16:29
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
         *配置不会被拦截的链接 顺序判断
         *过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
         *authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
         */
        //资源目录
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/scripts/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        //获得验证码
        filterChainDefinitionMap.put("/captcha/getCaptcha.jpg", "anon");
        //文件上传
        filterChainDefinitionMap.put("/upload/**", "anon");
        //视频网站前后台页面登录
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/login.do", "anon");
        filterChainDefinitionMap.put("/videoWebFront/index/index.html", "anon");
        filterChainDefinitionMap.put("/videoWebFront/index/singUp.do", "anon");
        filterChainDefinitionMap.put("/videoWebFront/index/forgPass.do", "anon");
        filterChainDefinitionMap.put("/parseVideoUrl/getPptvUrl", "anon");
        filterChainDefinitionMap.put("/videoWebFront/play/play.html", "anon");
        filterChainDefinitionMap.put("/videoWebFront/comment/getComments.page*", "anon");
        filterChainDefinitionMap.put("/videoWebFront/recommend/*", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/videoWebFront/index/loginOut.do", "logout");
        filterChainDefinitionMap.put("/logout.do", "logout");

        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/videoWebFront/index/index.html");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(myRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public Realm myRealm() {
        Realm myRealm = new Realm();
        return myRealm;
    }

    @Bean
    public HandlerExceptionResolver solver() {
        HandlerExceptionResolver handlerExceptionResolver = new MyExceptionResolver();
        return handlerExceptionResolver;
    }

}
