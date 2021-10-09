package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void addUser(User user) {
        manager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        manager.createQuery("delete FROM User u where u.id = :id")
        .setParameter("id",  id).executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return manager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String login) {
        return (User) manager.createQuery("from User where login=: login")
                .setParameter("login", login)
                .getSingleResult();

    }
}
