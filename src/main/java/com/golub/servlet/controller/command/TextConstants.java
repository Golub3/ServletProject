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
        String BOUGHT_TICKETS = "boughtTickets";
        String NO_OF_PAGES = "noOfPages";
        String USERS = "users";
        String TICKETS = "tickets";
        String SCHEDULES = "schedules";
        String EXPOSITIONS = "expositions";
        String HALLS = "halls";
        String SORT_FIELD = "sort_field";
        String DIR = "dir";
        String START_DATE = "start_date";
        String END_DATE = "end_date";
        String START_TIME = "time_start";
        String END_TIME = "time_end";
        String DATE = "date";
        String THEME = "theme";
        String PRICE = "price";
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
        String EXPOSITION_SUCCESS = "/WEB-INF/admin/createExposition.jsp?success=true";
        String PRICE_FAIL_INVALID_DATA = "/WEB-INF/admin/createExposition.jsp?priceInvalid=true";
        String THEME_FAIL_INVALID_DATA = "/WEB-INF/admin/createExposition.jsp?themeInvalid=true";

        String TO_HOME = "/WEB-INF/common/welcome.jsp";
        String TO_LOGIN = "/WEB-INF/common/login.jsp";
        String TO_REGISTRATION = "/WEB-INF/common/registration.jsp";
        String TO_SHOW_USERS = "/WEB-INF/admin/show-users.jsp";
        String TO_SHOW_SCHEDULES = "/WEB-INF/user/schedules.jsp";
        String TO_SHOW_SCHEDULES_FOR_ADMIN = "/WEB-INF/admin/schedules.jsp";
        String TO_SCHEDULE_CREATE = "/WEB-INF/admin/createSchedule.jsp";
        String TO_EXPOSITION_CREATE = "/WEB-INF/admin/createExposition.jsp";
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
        String SCHEDULE_CREATE_PAGE = "createSchedule";
        String EXPOSITION_CREATE_PAGE = "createExposition";

        //servlet
        String APPLICATION_PATH_REGEX = ".*/app/";
        String DEFAULT_PATH = "app/home";

        //actions
        String SHOW_USERS = "show-users";
        String SHOW_SCHEDULES = "schedules";
        String TICKET_BUY = "ticket";
        String SCHEDULE_DELETE = "deleteSchedule";
        String SCHEDULE_CREATE = "createSchedulePage";
        String EXPOSITION_CREATE = "createExpositionPage";
    }

}
