package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(`id` BIGINT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(45) NOT NULL," +
                    " `lastName` VARCHAR(45) NOT NULL," +
                    " `age` TINYINT NOT NULL," +
                    " PRIMARY KEY (id))", User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Table creating failed!");
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users",User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Table drop failed!");
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {;
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("User save failed!");
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Wrong id!");
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            allUsers = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Cannot obtain users list!");
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Failed to clean table!");
        } finally {
            session.close();
        }
    }
}