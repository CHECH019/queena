package com.cdevs.queene.appointment;

import java.util.List;

import com.cdevs.queene.generics.GenericService;

public interface AppointmentService extends GenericService<Appointment, Long>{
    public List<Appointment> getByEmployeeId(long id);
    public List<Appointment> getByClientId(long id);
}
