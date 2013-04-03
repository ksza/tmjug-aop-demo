package org.tmjug.spring.demo.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tmjug.spring.demo.entities.User;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public class UsersDAOImpl implements UsersDAO {

    private static final Logger log = LoggerFactory.getLogger(UsersDAOImpl.class);

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSpringDemoSessionFactory(SessionFactory springDemoSessionFactory) {
        hibernateTemplate = new HibernateTemplate(springDemoSessionFactory);
    }

    public User getByUserName(String userName) {
        List<User> users = hibernateTemplate.findByNamedQueryAndNamedParam("User.byUserName", "userName", userName);
        if (users == null || users.isEmpty()) {
            log.warn("There is no user named '{}'", userName);
            return null;
        }

        return users.get(0);
    }

    public List<User> getAll(String name) {
        if (name == null) {
            return hibernateTemplate.find("FROM User");
        } else {
            return hibernateTemplate.findByNamedQueryAndNamedParam("User.byName", "name", '%' + name.toLowerCase() + '%');
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void save(User user) {
        hibernateTemplate.save(user);
        hibernateTemplate.flush();
    }
}
