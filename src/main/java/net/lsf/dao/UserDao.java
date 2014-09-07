package net.lsf.dao;

import net.lsf.model.User;

import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:19 PM
 */
public interface UserDao {

    public User getUserByUserName(String userName);

    List<String> getAuthoritiesByUserName(String userName);

    void updatePasswordForUser(String userName, String newPassword);
}
