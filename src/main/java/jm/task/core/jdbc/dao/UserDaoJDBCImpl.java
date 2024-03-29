package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("create table if not exists userstest (id bigint not null auto_increment, name varchar(45) not null, lastName varchar(45) not null, age int(3) not null, PRIMARY KEY (id) )")) {
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Util.getConnection().rollback();
        }
    }

    public void dropUsersTable() throws SQLException {
        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("drop table if exists userstest")) {
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Util.getConnection().rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        User user = new User(name, lastName, age);
        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("insert into userstest (name, lastName, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.execute();
            System.out.println("New users with name: " + user.getName() + " added to table");
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Util.getConnection().rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        User user = new User(id);
        try (PreparedStatement preparedStatement = Util.getConnection()
                .prepareStatement("delete from userstest where id=?")) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Util.getConnection().rollback();
        }
    }


    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM userstest")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getByte("age")));
                Util.getConnection().commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Util.getConnection().rollback();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM userstest")) {
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Util.getConnection().rollback();
        }
    }
}
