package com.cdevs.queena.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdevs.queena.iServices.IAppointmentService;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.repository.AppointmentRepository;



@Service
public class AppointmentService implements IAppointmentService{
    @Autowired
    private AppointmentRepository repository;

    @Override
    public Appointment save(Appointment a) {
        return repository.save(a);
    }

    @Override
    public List<Appointment> getList() {
        return (List<Appointment>) repository.findAll();
    }
    
    @Override
    public Appointment getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Appointment update(Appointment a) {
        return repository.save(a);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Appointment> getByEmployeeId(long id){
        return (List<Appointment>) repository.findByEmployeeId(id);
    }

    public List<Appointment> getByClientId(long id){
        return (List<Appointment>) repository.findByClientId(id);
    }
        
}