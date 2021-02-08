package com.golub.servlet.model.dao;

import java.util.List;


/**
 * Standard CRUD operations provided.
 * @author Vitalii Holub
 */
public interface GenericDao<T> extends AutoCloseable{

    //Create
    void create(T entity);

    //Read
    T findById(long id);

    List<T> findAll();

    //Update
    void update(T t);

    //Delete
    void delete(long id);

    //Close
    void close();


}