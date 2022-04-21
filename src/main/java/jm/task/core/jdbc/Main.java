package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static final UserService userService = new UserServiceImpl();

    private static UserServiceImpl getUserService() {
        return (UserServiceImpl) userService;
    }

    public static void main(String[] args) throws SQLException {
        getUserService().createUsersTable();
        getUserService().saveUser("Niko", "Haizinberg", (byte) 27);
        getUserService().saveUser("Gleb", "Stolbov", (byte) 17);
        getUserService().saveUser("Vano", "Ivano", (byte) 20);
        getUserService().saveUser("Vasya", "Pupkin", (byte) 45);
        getUserService().getAllUsers();
        getUserService().cleanUsersTable();
        getUserService().dropUsersTable();
    }
}

