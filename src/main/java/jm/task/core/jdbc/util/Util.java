package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){

        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.hibernate.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mysql?currentSchema=testdb");
                settings.put(Environment.USER, "Scooby1468");
                settings.put(Environment.PASS, "filipokSS1968");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2016Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                org.hibernate.service.ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB", "Scooby1468", "filipokSS1968");
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }

    public static void shutdownConnect() throws SQLException {
        connection.close();
    }
}
