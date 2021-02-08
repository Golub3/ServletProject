package com.golub.servlet.model.dao.mapper;


import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static com.golub.servlet.model.entity.User.ROLE.getRoleById;
import static com.golub.servlet.model.entity.User.ROLE.getRoleByString;

public class UserMapper implements ObjectMapper<User>{

    private static final String ID_USER = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String BALANCE = "balance";
    private static final String ID_ROLE = "role";



    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        UserService userService = UserService.getInstance();

        user.setId(rs.getLong(ID_USER));

        user.setFirstName(rs.getString(FIRST_NAME));
        user.setLastName(rs.getString(LAST_NAME));

        user.setEmail(rs.getString(EMAIL));
        user.setPassword(rs.getString(PASSWORD));
        user.setBalance(rs.getBigDecimal(BALANCE));
        user.setRole(getRoleByString(
                rs.getString(ID_ROLE)));

        return user;
    }

    public User makeUnique(Map<Long, User> existing, User entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
