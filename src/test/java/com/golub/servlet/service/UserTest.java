package com.golub.servlet.service;

import com.golub.servlet.model.dao.DaoFactory;
import com.golub.servlet.model.dao.UserDao;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.exception.AlreadyExistingDBRecordException;
import com.golub.servlet.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private DaoFactory daoFactory;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUser_WhenFindById() {
        when(userDao.findById(anyLong())).thenReturn(getUser());
        User expected = getUser();
        User actual = userDao.findById(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetAllUsers() {
        //given
        int expectedNumber = 2;
        when(userDao.findAll())
                .thenReturn(givenUsersList());
        when(daoFactory.createUserDao())
                .thenReturn(userDao);
        //when
        List<User> users = userService.getAllUsers();
        //then
        assertEquals(users.size(), expectedNumber);
        assertEquals(users.get(0), new User.UserBuilder()
                .withId(1)
                .withFirstName("f")
                .withLastName("l")
                .withPassword("p")
                .withEmail("e1")
                .withBalance(new BigDecimal(0))
                .withRole(User.ROLE.USER).build());
    }

    List<User> givenUsersList() {
        return new ArrayList<User>(){
            {
                add(new User.UserBuilder()
                        .withId(1)
                        .withFirstName("f")
                        .withLastName("l")
                        .withPassword("p")
                        .withEmail("e1")
                        .withBalance(new BigDecimal(0))
                        .withRole(User.ROLE.USER).build());
                add(new User.UserBuilder()
                        .withId(1)
                        .withFirstName("f")
                        .withLastName("l")
                        .withPassword("p")
                        .withEmail("e2")
                        .withBalance(new BigDecimal(0))
                        .withRole(User.ROLE.USER).build());
            }};
    }

    private User getUser(){
        return new User.UserBuilder()
                .withId(1)
                .withFirstName("f")
                .withLastName("l")
                .withPassword("p")
                .withEmail("e")
                .withBalance(new BigDecimal(0))
                .withRole(User.ROLE.USER)
                .build();
    }
}
