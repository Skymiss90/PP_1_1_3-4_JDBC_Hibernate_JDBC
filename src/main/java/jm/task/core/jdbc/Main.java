package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Mari", "Smith", (byte) 18 );
        User user2 = new User("Leo", "Markes", (byte) 20 );
        User user3 = new User("Rudi", "Rubins", (byte) 22 );
        User user4 = new User("Teo", "Torres", (byte) 24 );
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        for (User user : userList) {
            userService.saveUser(user.getName(), user.getLastName(),user.getAge());
            System.out.println("User " + user.getName() + user.getLastName() + "added to the table");
        }
        List<User> selected = userService.getAllUsers();
        selected.stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
