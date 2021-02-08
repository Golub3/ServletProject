package com.golub.servlet.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;

import static com.golub.servlet.controller.command.TextConstants.Parameters.LOGGED_USERS;


/**
 * ContextListener put user loggedUsers to servlet context.
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

       final ServletContext servletContext = servletContextEvent.getServletContext();

       servletContext.setAttribute(LOGGED_USERS, new HashSet<String>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(LOGGED_USERS);
    }
}
