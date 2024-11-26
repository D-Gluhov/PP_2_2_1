package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .getResultList();
    }

    @Override
    public User findUserByCarModelAndSerial(String carModel, int carSerial) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.car.model = :carModel"
                             + " AND u.car.series = :carSerial", User.class)
                .setParameter("carModel", carModel)
                .setParameter("carSerial", carSerial)
                .getSingleResult();
    }
}
