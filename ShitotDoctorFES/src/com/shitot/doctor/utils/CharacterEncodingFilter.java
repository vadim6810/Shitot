package com.shitot.doctor.utils;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class CharacterEncodingFilter implements Filter {

    protected String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        encoding = null;
    }

}
