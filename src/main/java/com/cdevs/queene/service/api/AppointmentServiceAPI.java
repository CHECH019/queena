package com.cdevs.queene.service.api;

import java.util.List;

import com.cdevs.queene.generics.GenericServiceApi;
import com.cdevs.queene.model.Appointment;

public interface AppointmentServiceAPI extends GenericServiceApi<Appointment, Long>{
    public List<Appointment> getByEmployeeId(long id);
    public List<Appointment> getByClientId(long id);
}
