package com.example.demo.repository;

import com.example.demo.entity.StudentBookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class StudentBookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void create(StudentBookEntity entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public StudentBookEntity getById(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.find(StudentBookEntity.class, id);
        } catch (RuntimeException e) {
            return null;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public void update(StudentBookEntity entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public List<StudentBookEntity> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<StudentBookEntity> query = session.createQuery("from StudentBookEntity", StudentBookEntity.class);
            return query.getResultList();
        } catch (RuntimeException e) {
            return new LinkedList<>();
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
