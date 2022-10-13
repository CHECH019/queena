package com.cdevs.queena.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericRestController;
import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.service.api.AppointmentServiceAPI;
import com.cdevs.queena.constants.Constants;

@RestController
@RequestMapping(value = Constants.BASE_URL + "appointment")
public class AppointmentRestController extends GenericRestController<Appointment,Long>{

    @Autowired
    private AppointmentServiceAPI service;
    
    @Override
    public GenericServiceApi<Appointment, Long> getService() {
        return service;
    }
    
}