package com.golub.servlet.controller.command;


public interface TextConstants {

    interface Parameters {
        String SESSION_LOCALE_PARAMETER = "sessionLocale";
        String LANG_PARAMETER = "lang";
        String EMAIL = "email";
        String PASSWORD = "password";
        String ROLE = "role";
        String CONFIRM_PASSWORD = "confirmPassword";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String LOGGED_USERS = "loggedUsers";
        String BALANCE = "balance";
        String CURRENT_PAGE = "currentPage";
        String NO_OF_PAGES = "noOfPages";
        String USERS = "users";
        String SCHEDULES = "schedules";
        String SORT_FIELD = "sort_field";
        String DIR = "dir";
        String START_DATE = "start_date";
        String END_DATE = "end_date";
    }


    interface Routes {
        String REDIRECT = "redirect@";
        String EMPTY_STRING = "";
        String LOGIN_FAIL_INVALID_INPUT = "/WEB-INF/common/login.jsp?dataInvalid=true";
        String TO_PERSONAL_CABINET = "/app/personal-cabinet";
        String MULTILOGIN_ERROR = "/WEB-INF/common/error/multilogin.jsp";
        String INVALID_SESSION_ERROR = "/WEB-INF/common/error/invalidSession.jsp";
        String ACCESS_FORBIDDEN_403 = "/WEB-INF/common/error/403.jsp";

        String BASE = "/WEB-INF/common/base.jsp";
        String USER_NOT_EXIST = "/WEB-INF/common/login.jsp?userExist=false";

        String REGISTRATION_FAIL_INVALID_DATA = "/WEB-INF/common/registration.jsp?dataInvalid=true";
        String REGISTRATION_FAIL_PASSWORDS_DIFFERENT = "/WEB-INF/common/registration.jsp?passwordsDifferent=true";
        String REGISTRATION_FAIL_USER_EXIST = "/WEB-INF/common/registration.jsp?alreadyExist=true";
        String REGISTRATION_SUCCESS = "/WEB-INF/common/registration.jsp?success=true";

        String TO_HOME = "/WEB-INF/common/welcome.jsp";
        String TO_LOGIN = "/WEB-INF/common/login.jsp";
        String TO_REGISTRATION = "/WEB-INF/common/registration.jsp";
        String TO_SHOW_USERS = "/WEB-INF/admin/show-users.jsp";
        String TO_SHOW_SCHEDULES_USER = "/WEB-INF/user/schedules.jsp";
    }

    interface CommandPaths {
        //account
        String REGISTRATION = "registration";
        String LOGIN = "login";
        String LOGOUT = "logout";
        String PERSONAL_CABINET = "personal-cabinet";

        //directions
        String HOME = "home";
        String REG_ME = "reg-me";
        String LOG_ME = "log-me";

        //servlet
        String APPLICATION_PATH_REGEX = ".*/app/";
        String DEFAULT_PATH = "app/home";

        //actions
        String SHOW_USERS = "show-users";
        String SHOW_SCHEDULES_USER = "schedules";
    }

}