package com.cdevs.queena.commons;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class GenericRestController <T,ID extends Serializable>{
    private static final String endpt1 = "/save-T";
    private static final String endpt2 = "/save-T";
    private static final String endpt3 = "/save-T";
    private static final String endpt4 = "/save-T";

    
    @GetMapping(endpt1)
    public List<T> getAll(Model model){
        return getService().getAll();
    }

    @PostMapping(endpt2)
    public ResponseEntity<T> save(@RequestBody T entity){
        T e = getService().save(entity); 
        return new ResponseEntity<T>(e, HttpStatus.OK);
    }

    @GetMapping(endpt3)
    public ResponseEntity<T> get(@PathVariable ID id, Model model){
        T e = getService().get(id);
        if(e != null)
            getService().get(id);
        else
            return new ResponseEntity<T>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<T>(e,HttpStatus.OK);
    }

    @GetMapping(endpt4)
    public ResponseEntity<T> delete(@PathVariable ID id){
        T e = getService().get(id);
        if(e != null)
            getService().delete(id);
        else
            return new ResponseEntity<T>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<T>(e,HttpStatus.OK);
    }
    
    public abstract GenericServiceApi<T,ID> getService();

}