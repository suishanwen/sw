package com.sw.base.filter;

import java.io.IOException;
import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@Singleton
@WebFilter({"/api/*"})
public class UTF8Encoding implements Filter {
    public UTF8Encoding() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("utf-8");
        ((HttpServletResponse)servletResponse).addHeader("Content-Encoding", "utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}