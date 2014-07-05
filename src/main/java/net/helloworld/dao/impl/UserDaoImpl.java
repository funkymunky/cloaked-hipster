package net.helloworld.dao.impl;

import net.helloworld.dao.UserDao;
import net.helloworld.model.Authority;
import net.helloworld.model.User;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:20 PM
 */
public class UserDaoImpl implements UserDao {

    HibernateTemplate hibernateTemplate;

    private  String queryString = "from User where username = ?";

    public void setHibernateTemplate(HibernateTemplate arg0) {
        hibernateTemplate = arg0;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Override
    public User getUserByUserName(String userName) {
        return (User) hibernateTemplate.find(queryString, userName).get(0);
    }

    @Override
    public List<String> getAuthoritiesByUserName(String userName) {
        User user = (User) hibernateTemplate.find(queryString, userName).get(0);
        Authority authority = user.getAuthority();
        String auth = authority.getAuthority();
        List<String> authorityList = new ArrayList<String>();
        authorityList.add(auth);

        return authorityList;
    }
}
