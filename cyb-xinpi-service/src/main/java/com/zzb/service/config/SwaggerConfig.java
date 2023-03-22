package com.zzb.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    //  http://localhost:8088/swagger-ui.html 原路径
    //  http://localhost:8088/doc.html 原路径
    //配置swagger2核心配置
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //指定api类型位swagger2
                .apiInfo(apiInfo())            //用于定义api文档汇总信息
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.zzb.service.controller")) //指定生成文档的controller
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2") //文档标题
                .contact(new Contact("骈志刚", //作者
                        "http://localhost:8080/swagger-ui.html#/",
                        "16634445414@126.com")) //联系人
                .description("信披的项目api接口")//详细信息
                .version("1.0.0")//文档版本号
                .termsOfServiceUrl("\"http://www.apache.org/licenses/LICENSE-2.0\"")//网站地址
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");


    }
}