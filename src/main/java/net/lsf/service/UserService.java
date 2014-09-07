package net.lsf.service;

import net.lsf.model.User;

import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:26 PM
 */
public interface UserService {

    User getUserByUserName(String userName);

    List<String> getAuthoritiesByUserName(String userName);

    void updateUserPassword(String userName, String newPassword);

}
