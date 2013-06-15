package net.helloworld.service;

import net.helloworld.dao.UserDao;
import net.helloworld.model.User;

import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:27 PM
 */
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public List<String> getAuthoritiesByUserName(String userName) {
        return userDao.getAuthoritiesByUserName(userName);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
