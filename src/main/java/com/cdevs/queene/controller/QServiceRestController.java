package com.cdevs.queene.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queene.config.Constants;
import com.cdevs.queene.model.QService;
import com.cdevs.queene.service.api.GenericServiceApi;
import com.cdevs.queene.service.api.QServiceServiceAPI;

@RestController
@RequestMapping(value = Constants.BASE_URL + "service")
public class QServiceRestController extends GenericRestController<QService,Integer>{
    
    @Autowired
    private QServiceServiceAPI service;

    @Override
    public GenericServiceApi<QService, Integer> getService() {
        return service;
    }
   
}