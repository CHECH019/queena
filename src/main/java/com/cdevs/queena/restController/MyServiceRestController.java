package com.cdevs.queena.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericRestController;
import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.constants.Constants;
import com.cdevs.queena.model.MyService;
import com.cdevs.queena.service.api.MyServiceServiceAPI;

@RestController
@RequestMapping(value = Constants.BASE_URL + "service")
public class MyServiceRestController extends GenericRestController<MyService,Integer>{
    
    @Autowired
    private MyServiceServiceAPI service;

    @Override
    public GenericServiceApi<MyService, Integer> getService() {
        return service;
    }
   
}