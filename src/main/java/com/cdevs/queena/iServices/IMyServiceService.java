package com.cdevs.queena.iServices;

import java.util.List;

import com.cdevs.queena.model.MyService;


public interface IMyServiceService {
    public List<MyService> getList();
    public MyService getById(int id);
    public MyService save(MyService s);
    public void delete(int id);
    public MyService update(MyService s);
      
}
