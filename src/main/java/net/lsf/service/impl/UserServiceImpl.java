package net.lsf.service.impl;

import net.lsf.dao.UserDao;
import net.lsf.model.User;
import net.lsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:27 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public List<String> getAuthoritiesByUserName(String userName) {
        return userDao.getAuthoritiesByUserName(userName);
    }

    @Override
    public void updateUserPassword(String userName, String newPassword) {
        userDao.updatePasswordForUser(userName, newPassword);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
