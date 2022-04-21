package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao = new UserDaoJDBCImpl();

    public UserServiceImpl() {
    }

    private static UserDaoJDBCImpl getUserDao() {
        return (UserDaoJDBCImpl) userDao;
    }


    public void createUsersTable() throws SQLException {
        getUserDao().createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        getUserDao().dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        getUserDao().saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        getUserDao().removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return getUserDao().getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        getUserDao().cleanUsersTable();
    }
}
