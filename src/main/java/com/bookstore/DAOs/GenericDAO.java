package com.bookstore.DAOs;

import java.util.List;
import java.util.Set;

public interface GenericDAO<E> {

    public E create(E entity);

    public E update(E entity);

    public E get(Object id);

    public void delete(Object id);

    public List<E> listAll();

}
