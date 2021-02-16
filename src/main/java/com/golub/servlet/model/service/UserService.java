package com.golub.servlet.model.service;


import com.golub.servlet.model.dao.DaoFactory;
import com.golub.servlet.model.dao.UserDao;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.exception.AlreadyExistingDBRecordException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;


/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Vitalii Holub
 */
public class UserService {

    private DaoFactory daoFactory;
    private static UserService instance;

    private UserService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }

        return instance;
    }

    /**
     * Registers user's account if such not exist yet.
     *
     * @param user User.
     */
    public void registerUserAccount(User user) throws AlreadyExistingDBRecordException {
        try(UserDao userDao = daoFactory.createUserDao()) {
            if (userDao.emailIsAlreadyTaken(user.getEmail())) {
                throw new AlreadyExistingDBRecordException("Failed registering already existing user email " +
                        user.getEmail());
            }
            userDao.create(user);
        }
    }

    /**
     * obtains student by email.
     *
     * @param email String.
     */
    public User getUserByEmail(String email) {

        List<User> users = getAllUsers();

        return users.stream()
                .filter(student -> email.equals(student.getEmail()))
                .findAny()
                .orElse(null);
    }

    /**
     * checks if such user exist in db.
     *
     * @param email String.
     */
    public boolean isExistingUser(String email, String password) {
        UserDao dao = daoFactory.createUserDao();
        return dao.userIsExist(email, password);

    }


    /**
     * obtain role by email and password.
     *
     * @param email    String.
     * @param password String.
     */
    public User.ROLE getRoleByEmailAndPass(String email, String password) {
        UserDao dao = daoFactory.createUserDao();
        return dao.getRoleByEmailPassword(email, password);

    }


    /**
     * obtains student by id.
     *
     * @param id long.
     */
    public User getUserById(long id) {
        UserDao dao = daoFactory.createUserDao();
        return dao.findById(id);
    }

    /**
     * obtains student by id.
     *
     * @param id long.
     */
    public void setBalanceById(BigDecimal balance, long id) {
        UserDao dao = daoFactory.createUserDao();
        dao.alterBalanceById(balance, id);
    }


    /**
     * obtains List of all students.
     */
    public List<User> getAllUsers() {
        UserDao dao = daoFactory.createUserDao();
        return dao.findAll();

    }

    /**
     * Obtains List of users.
     */
    public PaginationResult getUsersByPagination(int lowerBound, int upperBound) {
        UserDao dao = daoFactory.createUserDao();
        return dao.findByPagination(lowerBound, upperBound);
    }

    /**
     * It is user-defined class just for returning result from findByPagination() method.
     */
    public static class PaginationResult {
        private int noOfRecords;
        private List<User> resultList;

        public int getNoOfRecords() {
            return noOfRecords;
        }

        public void setNoOfRecords(int noOfRecords) {
            this.noOfRecords = noOfRecords;
        }

        public List<User> getResultList() {
            return resultList;
        }

        public void setResultList(List<User> resultList) {
            this.resultList = resultList;
        }

    }

}
