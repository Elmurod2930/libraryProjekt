package com.example.demo.repository;

import com.example.demo.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public BookEntity saveStudent(BookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);

        transaction.commit();
        session.close();
        return book;
    }

    public List<BookEntity> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<BookEntity> query = session.createQuery("from  books ", BookEntity.class);
            return query.getResultList();
        } catch (RuntimeException e) {
            return new LinkedList<>();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public BookEntity getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.find(BookEntity.class, id);
        } catch (RuntimeException e) {
            return null;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public Boolean delete(BookEntity entity) {
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

    public Boolean update(BookEntity entity) {
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
