package com.cdevs.queena.generics;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cdevs.queena.global.Constants;

public abstract class GenericRestController <T,ID extends Serializable>{

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll(Model model, HttpServletRequest request){
        String role = (String) request.getAttribute("role");
        if(role.equals(Constants.ROLE_ADMIN)){
            return new ResponseEntity<>(getService().getAll(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }

    @PostMapping("/save")
    public ResponseEntity<T> save(@RequestBody T entity){
        T e = getService().save(entity); 
        return new ResponseEntity<T>(e, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<T> get(@PathVariable ID id, Model model, HttpServletRequest request){
        System.out.println(id);
        T e = getService().get(id);
        System.out.println(e);
        if(e != null){
            String role = (String) request.getAttribute("role");
            if(role.equals(Constants.ROLE_ADMIN)){
                return new ResponseEntity<>(getService().get(id),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
            }
        }else{
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<T> delete(@PathVariable ID id, HttpServletRequest request){
        T e = getService().get(id);
        if(e != null){
            String role = (String) request.getAttribute("role");
            if(role.equals(Constants.ROLE_ADMIN)){
                getService().delete(id);
                return new ResponseEntity<>(e,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
            }
        }
        else{
            return new ResponseEntity<T>(e,HttpStatus.NO_CONTENT);
        }
    }
    
    public abstract GenericServiceApi<T,ID> getService();

}