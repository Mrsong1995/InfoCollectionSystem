package com.key.config;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;

/**
 * @author jesse
 * @Date 2020-07-04 23:15
 * @Version 1.0
 */

@Configuration
public class MyWebApplicationInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext sc) {
        sc.addListener(new Log4jServletContextListener());
    }

}
