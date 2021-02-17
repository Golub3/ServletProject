package com.golub.servlet.controller.filters;

import com.golub.servlet.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.*;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.HOME;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.LOGIN;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.LOGOUT;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.LOG_ME;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.PERSONAL_CABINET;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.REGISTRATION;
import static com.golub.servlet.controller.command.TextConstants.CommandPaths.REG_ME;
import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Parameters.ROLE;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.ACCESS_FORBIDDEN_403;
import static com.golub.servlet.controller.command.TextConstants.Routes.EMPTY_STRING;
import static com.golub.servlet.controller.command.TextConstants.Routes.TO_LOGIN;


public class AccessFilter implements Filter {
    private Map<User.ROLE, Set<String>> allowedRoutes;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedRoutes = new HashMap<>();
        allowedRoutes.put(User.ROLE.UNKNOWN,
                Stream.of(EMPTY_STRING, REG_ME, LOG_ME, LOGIN, REGISTRATION, HOME)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));

        allowedRoutes.put(User.ROLE.USER,
                Stream.of(LOGOUT, PERSONAL_CABINET, SHOW_SCHEDULES, TICKET_BUY)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));

        allowedRoutes.put(User.ROLE.ADMIN,
                Stream.of(LOGOUT, PERSONAL_CABINET, SHOW_USERS, SHOW_SCHEDULES, SCHEDULE_DELETE, TO_SCHEDULE_CREATE,
                        SCHEDULE_CREATE, SCHEDULE_CREATE_PAGE, TO_EXPOSITION_CREATE,
                        EXPOSITION_CREATE, EXPOSITION_CREATE_PAGE)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String path = request.getRequestURI()
                .replace(request.getContextPath(), EMPTY_STRING)
                .replace(request.getServletPath(), EMPTY_STRING)
                .replace("/", EMPTY_STRING);

        if (request.getSession().getAttribute(ROLE) == null) {
            request.getSession().setAttribute(ROLE, User.ROLE.UNKNOWN);
        }
        User.ROLE currentRole = ((User.ROLE) request.getSession().getAttribute(ROLE));

        if (allowedRoutes.get(currentRole).contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //user is guest? then sign in
            if (currentRole.equals(User.ROLE.UNKNOWN)) {
                request.getRequestDispatcher(TO_LOGIN).forward(request, response);
            } else {
                request.getRequestDispatcher(ACCESS_FORBIDDEN_403).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
