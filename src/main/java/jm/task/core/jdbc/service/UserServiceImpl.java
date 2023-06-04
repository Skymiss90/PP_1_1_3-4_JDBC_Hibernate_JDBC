package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDao userDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        //userDaoJDBC.createUsersTable();
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
    // userDaoJDBC.dropUsersTable();
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        //userDaoJDBC.removeUserById(id);
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDaoJDBC.getAllUsers();
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        //userDaoJDBC.cleanUsersTable();
        userDao.cleanUsersTable();
    }
}
