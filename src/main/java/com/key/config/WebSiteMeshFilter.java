package com.key.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;



public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        //除了/admin/index和/admin/login页面外，其他所有/admin/下的页面都被/admin/index页面所装饰
        builder.addDecoratorPath("/design/my-survey-design/*", "/WEB-INF/page/layouts/default.jsp")
                .addExcludedPath("/design/my-survey-design/execute")
                .addDecoratorPath("/survey/*","/WEB-INF/page/layouts/default.jsp")
                .addDecoratorPath("/dwsurvey/*","/WEB-INF/page/layouts/default.jsp")
                .addDecoratorPath("/dws-answer/*","/WEB-INF/page/layouts/default.jsp");
        builder.addExcludedPath("/js/dw/html/collectset.html");
    }

}
