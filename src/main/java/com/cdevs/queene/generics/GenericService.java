package com.cdevs.queene.generics;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {
    T save (T entity);
    T update (T update);
    void deleteById(ID id);
    T getById(ID id);
    List<T> getAll();
}