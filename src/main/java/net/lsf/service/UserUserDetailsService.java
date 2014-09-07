package net.lsf.service;

import net.lsf.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:48 PM
 */
public class UserUserDetailsService implements UserDetailsService {

    private UserService userService;

    public UserUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        User user;

        try {
            user = userService.getUserByUserName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("getUserByUserName returned null.");
        }

        List<String> authorities = userService.getAuthoritiesByUserName(userName);
        user.setUserAuthorities(authorities);

        return (UserDetails) user;
    }
}
