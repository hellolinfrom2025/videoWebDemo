package org.mintleaf.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: MengchuZhang
 * @Date: 2018/9/28 10:33
 * @Version 1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //选择那些路径和api会生成document
                .select()
                .apis(RequestHandlerSelectors.any())
                //对所有api进行监控
                .paths(PathSelectors.any())
                //错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("MintLeaf-Fast接口列表")
                .description("基于swagger生成接口文档,可在线对接口进行调试！")
                .contact(new Contact("ZhangMencghu", "https://gitee.com/ZhangMengchu/projects", "6153629@qq.com"))//作者
                .version("1.0")
                .build();

    }

}