package com.golub.servlet.model.dao;


import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.UserService;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    User.ROLE getRoleByEmailPassword(final String email, final String password);

    boolean userIsExist(final String email, final String password);

    boolean emailIsAlreadyTaken(final String email);

    UserService.PaginationResult findByPagination(int offset, int noOfRecords);
}
