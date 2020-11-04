/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.key.common.plugs.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Xss保护过滤器
 *
 * @author storezhang
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Xss filter inited!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpWrapper xssRequest = new XssHttpWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
        System.out.println("Xss filter destroyed!");
    }
}