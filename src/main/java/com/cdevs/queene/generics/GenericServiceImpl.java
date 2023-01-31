package com.cdevs.queene.generics;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<T,ID extends Serializable> implements GenericService<T,ID> {

    @Override
    public T save(T entity) {
        return getDAO().save(entity);
    }

    @Override
    public void deleteById(ID id) {
        getDAO().deleteById(id);
    }

    @Override
    public T getById(ID id) {
        Optional<T> obj = getDAO().findById(id);
        return obj.orElse(null);
    }

    @Override
    public List<T> getAll() {
        return (List<T>) getDAO().findAll();
    }
    
    public abstract CrudRepository<T,ID> getDAO();
}
