package com.cdevs.queena.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queena.commons.GenericServiceImpl;
import com.cdevs.queena.dao.AppointmentDaoApi;
import com.cdevs.queena.exceptions.QAuthException;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.service.api.AppointmentServiceAPI;

@Service
public class AppointmentServiceImpl extends GenericServiceImpl<Appointment,Long> implements AppointmentServiceAPI{
    @Autowired
    private AppointmentDaoApi dao;

    @Override
    public List<Appointment> getByEmployeeId(long id){
        return (List<Appointment>) dao.findByEmployeeId(id);
    }
    
    @Override
    public List<Appointment> getByClientId(long id){
        return (List<Appointment>) dao.findByClientId(id);
    }

    @Override
    public CrudRepository<Appointment, Long> getDAO() {
        return dao;
    }

    @Override
    public Appointment save(Appointment entity) {
        List<Appointment> list = getByEmployeeId(entity.getEmployee().getId());
        if(entity.getServices() == null)
            throw new QAuthException("Debe ingresar servicios");
        for(Appointment a : list){
            if(a.getLdt().equals(entity.getLdt())){
                throw new QAuthException("HORARIO NO DISPONIBLE");
            }
        }
        return super.save(entity);
    }
        
}