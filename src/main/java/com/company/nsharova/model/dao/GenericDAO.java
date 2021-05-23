package com.company.nsharova.model.dao;
import java.util.List;


public interface GenericDAO<T> extends AutoCloseable{
    void create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
