package com.golub.servlet.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Common interface for all commands.
 * @author Vitalii Holub.
 */
public interface Command {
    /**
     * This is the executing certain command method
     * which provides the concrete logic for each
     * class that implements it.
     *
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     * @return path to forward or redirect.
     * In case redirecting it adds "redirect@" marker
     * which truncates in Servlet method-processor.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
