package com.cdevs.queena.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericRestController;
import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.constants.Constants;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;


@RestController
@RequestMapping(value = Constants.BASE_URL + "client")
public class ClientRestController extends GenericRestController<Client,Long>{

    @Autowired
    private ClientServiceAPI service;
    
    @Override
    public GenericServiceApi<Client, Long> getService() {
        return service;
    }

}