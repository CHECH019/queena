package com.cdevs.queena.controller.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cdevs.queena.commons.GenericServiceApi;

abstract public class GenericRestController <T,ID extends Serializable>{
    protected static final String BASE_URL = "api/v1/";

    @GetMapping("/all")
    public List<T> getAll(Model model){
        return getService().getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<T> save(@RequestBody T entity){
        T e = getService().save(entity); 
        return new ResponseEntity<T>(e, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<T> get(@PathVariable ID id, Model model){
        T e = getService().get(id);
        if(e != null)
            getService().get(id);
        else
            return new ResponseEntity<T>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<T>(e,HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<T> delete(@PathVariable ID id){
        T e = getService().get(id);
        if(e != null)
            getService().delete(id);
        else
            return new ResponseEntity<T>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<T>(e,HttpStatus.OK);
    }
    
    public abstract GenericServiceApi<T,ID> getService();

    public static String getBaseUrl() {
        return BASE_URL;
    }
}