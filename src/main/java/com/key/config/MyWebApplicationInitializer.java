package com.key.config;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Configuration
public class MyWebApplicationInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext sc) {
        sc.addListener(new Log4jServletContextListener());
    }

}
