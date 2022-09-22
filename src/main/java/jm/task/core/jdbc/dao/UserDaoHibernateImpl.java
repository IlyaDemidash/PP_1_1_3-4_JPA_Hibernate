package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static Session session;
    private static final SessionFactory sessionFactory = Util.getFactory();
    private static final String createTableUsers = "CREATE TABLE IF NOT EXISTS users (id int not null AUTO_INCREMENT, name VARCHAR(20)," +
            "lastName VARCHAR(20), age INT, primary key (id))";
    private static final String dropIfExist = "DROP TABLE IF EXISTS users";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(createTableUsers).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(dropIfExist).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name, lastName, age);
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User where id = " + id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> usersToList = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
            return usersToList;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
