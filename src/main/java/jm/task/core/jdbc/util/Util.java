package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final String dbName = "userdb";
    public static final String dbTableName = "users";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/userdb";
    private static final String dbUserName = "root";
    private static final String dbUserPass = "admin";

    public static Connection connectionJDBC = getConnectionJDBC();

    public static Connection getConnectionJDBC() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbUserPass);

        } catch (SQLException e) {
            System.out.println("Connection failed...");
            throw new RuntimeException(e);
        }
        return connection;

    }

    public static SessionFactory getSessionFactory() {


        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, dbUrl);
        properties.put(Environment.USER, dbUserName);
        properties.put(Environment.PASS, dbUserPass);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.HBM2DDL_AUTO, "none");
        return new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
    }
}


