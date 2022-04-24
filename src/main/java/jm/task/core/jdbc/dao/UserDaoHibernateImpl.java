package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {
        String sql = "create table if not exists users (id bigint not null auto_increment, name varchar(45) not null, lastName varchar(45) not null, age int(3) not null, PRIMARY KEY (id)  )";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try (session){
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table if exists users";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try (session){
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try(session) {
            transaction = session.beginTransaction();
            session.save(user);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = new User(id);
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try (session){
            transaction = session.beginTransaction();
            user.setId(user.getId());
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try(session){
            transaction = session.beginTransaction();
            userList = session.createQuery("from User ").getResultList();
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null)
            transaction.rollback();
        }
        return userList;
    }


    @Override
    public void cleanUsersTable() {
        String sql = "delete from users";
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;
        try (session){
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }
    }
}
