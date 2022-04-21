package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
    String sql = "create table if not exists users (id bigint not null auto_increment, name varchar(45) not null, lastName varchar(45) not null, age int(3) not null, PRIMARY KEY (id)  )";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
    String sql = "drop table if exists users";
    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createSQLQuery(sql).addEntity(User.class);
    query.executeUpdate();
    transaction.commit();
    session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
    User user = new User(name, lastName, age);
    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(user);
    session.flush();
    session.close();
    }

    @Override
    public void removeUserById(long id) {
    User user = new User(id);
    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    user.setId(user.getId());
    session.delete(user);
    transaction.commit();
    session.close();

    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> list = session.createCriteria(User.class).list();
        session.close();
        System.out.println(list.toString());
        return list;
    }



    @Override
    public void cleanUsersTable() {
    String sql = "delete from users";
    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createSQLQuery(sql).addEntity(User.class);
    query.executeUpdate();
    transaction.commit();
    session.close();
    }
}
