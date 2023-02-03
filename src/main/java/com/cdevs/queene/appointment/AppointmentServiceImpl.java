package com.cdevs.queene.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cdevs.queene.generics.GenericServiceImpl;

@Service
public class AppointmentServiceImpl extends GenericServiceImpl<Appointment,Long> implements AppointmentService{
    
    @Autowired
    private AppointmentDao dao;

    @Override
    public List<Appointment> getByEmployeeId(long id){
        return null;
    }
    
    @Override
    public List<Appointment> getByClientId(long id){
        return null;
    }

    @Override
    public CrudRepository<Appointment, Long> getDAO() {
        return dao;
    }

    @Override
    public Appointment save(Appointment entity) {
        if(entity.getClient() == null){
            // throw new QAuthException("Invalid client id");
        }
        if(entity.getEmployee() == null){
            // throw new QAuthException("Invalid Employee id");
        }
        if(entity.getServices().contains(null)){
            // throw new QAuthException("Some Invalid Service id");
        }
        if(entity.getServices() == null){
            // throw new QAuthException("Appointment's services not provided");
        }

        List<Appointment> clientAps = getByClientId(entity.getClient().getId());
        clientAps.forEach(a ->{
            if(a.getLdt().equals(entity.getLdt())){
                // throw new QAuthException("You already have an appointment at "+entity.getLdt().toString());
            }
        });
        List<Appointment> employeeAps = getByEmployeeId(entity.getEmployee().getId());
        for(Appointment a : employeeAps){
            if(a.getLdt().equals(entity.getLdt())){
                // throw new QAuthException("Employee not available at "+entity.getLdt().toString());
            }
        }
        entity.setStatus("pendiente");
        return super.save(entity);
    }

    @Override
    public Appointment update(Appointment update) {
        // TODO Auto-generated method stub
        return null;
    }
    
}