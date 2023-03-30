package com.example.demo.repository;

import com.example.demo.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public StudentEntity saveStudent(StudentEntity student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);

        transaction.commit();
        session.close();
        return student;
    }

    public List<StudentEntity> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<StudentEntity> query = session.createQuery("from StudentEntity ", StudentEntity.class);
            return query.getResultList();
        } catch (RuntimeException e) {
            return new LinkedList<>();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public StudentEntity getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.find(StudentEntity.class, id);
        } catch (RuntimeException e) {
            return null;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public Boolean delete(StudentEntity entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            return true;
        } catch (RuntimeException e) {
            return false;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public Boolean update(StudentEntity entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            return true;
        } catch (RuntimeException e) {
            return false;
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
