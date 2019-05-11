package org.mintleaf.config;

import com.ibeetl.starter.BeetlTemplateCustomize;
import org.beetl.core.GroupTemplate;
import org.mintleaf.common.ShiroExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author by 15919
 * @createDate 2019/5/11 10:55
 */
@Configuration
public class MyBeetlConfig {
    @Bean
    public BeetlTemplateCustomize beetlTemplateCustomize() {
        return new BeetlTemplateCustomize() {
            public void customize(GroupTemplate groupTemplate) {
                //将实现了shiro标签的beetl方法注册到groupTemplate里
                groupTemplate.registerFunctionPackage("so", new ShiroExt());
            }
        };
    }
}


