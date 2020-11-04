package com.key.config;

import com.key.common.plugs.xss.XssFilter;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jesse
 * @Date 2020-07-03 10:26
 * @Version 1.0
 */

@Configuration
@ComponentScan({"com.key.common.service", "com.key.dwsurvey.service","com.key.common.base.service"})
public class ApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);
    private StrutsPrepareAndExecuteFilter strutsPrepareAndExecuteFilter = new StrutsPrepareAndExecuteFilter(); //springboot启动时初始化struts2拦截器
    @Bean
    @Order(1)
    public FilterRegistrationBean<UrlRewriteFilter> urlRewriteFilter(){
        log.info(" Filter 定义！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        FilterRegistrationBean<UrlRewriteFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UrlRewriteFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD);
        return registrationBean;
    }

    @Bean
    @Order(2)
    public FilterRegistrationBean<CharacterEncodingFilter> encodingFilter(){
        log.info("Character Encoding filter！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
        registrationBean.setFilter(new CharacterEncodingFilter());
        registrationBean.addUrlPatterns("/*");
        Map<String,String> encoding = new HashMap<String, String>();
        encoding.put("encoding","UTF-8");
        encoding.put("forceEncoding","true");
        registrationBean.setInitParameters(encoding);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD);
        return registrationBean;
    }

    @Bean
    @Order(3)
    public FilterRegistrationBean<XssFilter> xssFilter(){
        log.debug(" 配置Java Xss保护过滤器！！！");
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns("/*/execute");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD);
        return registrationBean;
    }




    @Bean
    @Order(5)
    public FilterRegistrationBean<DelegatingFilterProxy> shiroFilter(){
        log.debug("Shiro Filter");
        FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
        registrationBean.setFilter(new DelegatingFilterProxy());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD);
        return registrationBean;
    }
    @Bean()
    @Order(6)
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy(){
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        //filterRegistrationBean.setAsyncSupported(true);
        EnumSet<DispatcherType> types = EnumSet.of(DispatcherType.REQUEST,
        DispatcherType.FORWARD);
        filterRegistrationBean.setDispatcherTypes(types);

        return filterRegistrationBean;
    }

    @Bean
    @Order(7)
    public FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter() {
        log.info("SiteMeshFilter!!!");
        FilterRegistrationBean<ConfigurableSiteMeshFilter> fitler = new FilterRegistrationBean<ConfigurableSiteMeshFilter>();

        //实例化一个过滤器类

        WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();

        fitler.setFilter(siteMeshFilter);

        return fitler;

    }
    @Bean
    @Order(8)
    public FilterRegistrationBean<StrutsPrepareAndExecuteFilter> strutsPrepareAndExecuteFilter() {
       /*
       <filter>
		<filter-name>struts2Filter</filter-name>
		<filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
		<init-param>
			<param-name>config</param-name>
			<param-value>struts-default.xml,
						 struts-plugin.xml,
						 /conf/struts.xml
			</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>struts2Filter</filter-name>
		<url-pattern>*.controller</url-pattern>
		<dispatcher>REQUEST</dispatcher>
		<dispatcher>FORWARD</dispatcher>
	</filter-mapping>
        */
        log.debug("Instantiating StrutsPrepareAndExecuteFilter");
        FilterRegistrationBean<StrutsPrepareAndExecuteFilter> registrationBean = new FilterRegistrationBean<StrutsPrepareAndExecuteFilter>();
        registrationBean.setFilter(strutsPrepareAndExecuteFilter);//注册Struts2拦截器
        registrationBean.setName("StrutsPrepareAndExecuteFilter");
        Map<String,String> config = new HashMap<String, String>();
        config.put("config","struts-default.xml,struts-plugin.xml,/conf/struts.xml");

        registrationBean.addUrlPatterns("/*");//默认拦截所有请求
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD);
        return registrationBean;
    }



}
