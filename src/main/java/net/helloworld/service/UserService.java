package net.helloworld.service;

import net.helloworld.model.User;

import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:26 PM
 */
public interface UserService {

    User getUserByUserName(String userName);

    List<String> getAuthoritiesByUserName(String userName);

}
