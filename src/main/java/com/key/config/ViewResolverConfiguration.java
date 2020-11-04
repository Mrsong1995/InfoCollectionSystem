package com.key.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ViewResolverConfiguration {
    @Configuration//用来定义 DispatcherServlet 应用上下文中的 bean
    @EnableWebMvc
    @ComponentScan("com.key.dwsurvey.controller.*")
    public class WebConfig implements WebMvcConfigurer {


        @Bean
        public InternalResourceViewResolver htmlViewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setViewClass(HandleResourceViewExists.class);
            viewResolver.setPrefix("/WEB-INF/wjHtml");
            viewResolver.setSuffix(".html");
            viewResolver.setOrder(1);
            viewResolver.setContentType("text/html;charset=UTF-8");
            return viewResolver;
        }

        @Bean
        public InternalResourceViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setViewClass(HandleResourceViewExists.class);
            viewResolver.setPrefix("/WEB-INF/page/");
            viewResolver.setSuffix(".jsp");
            viewResolver.setOrder(0);
            viewResolver.setContentType("text/html;charset=UTF-8");
            return viewResolver;
        }


        @Bean
        public InternalResourceViewResolver viewResolverError() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setViewClass(HandleResourceViewExists.class);
            viewResolver.setPrefix("/common/");
            viewResolver.setSuffix(".jsp");
            viewResolver.setOrder(2);
            viewResolver.setContentType("text/html;charset=UTF-8");
            return viewResolver;
        }


        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }

    }
}
