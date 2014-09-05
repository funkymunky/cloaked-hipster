package net.helloworld.dao.impl;

import net.helloworld.dao.UserDao;
import net.helloworld.model.Authority;
import net.helloworld.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    HibernateTemplate hibernateTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    private  String queryString = "from User where username = ?";

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

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

    @Override
    public void updatePasswordForUser(String userName, String newPassword) {
        User user = (User) hibernateTemplate.find(queryString, userName).get(0);
        user.setPassword(newPassword);
        getCurrentSession().update(user);
    }
}
