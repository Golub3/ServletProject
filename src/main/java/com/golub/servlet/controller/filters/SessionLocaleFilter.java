package com.golub.servlet.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.Parameters.LANG_PARAMETER;
import static com.golub.servlet.controller.command.TextConstants.Parameters.SESSION_LOCALE_PARAMETER;

public class SessionLocaleFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter(SESSION_LOCALE_PARAMETER) != null) {
            req.getSession().setAttribute(LANG_PARAMETER, req.getParameter(SESSION_LOCALE_PARAMETER));
        }
        chain.doFilter(request, response);
    }
    public void destroy() {}
    public void init(FilterConfig arg0) throws ServletException {}
}
