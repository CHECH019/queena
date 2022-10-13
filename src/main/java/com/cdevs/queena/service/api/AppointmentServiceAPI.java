package com.cdevs.queena.service.api;

import java.util.List;

import com.cdevs.queena.generics.GenericServiceApi;
import com.cdevs.queena.model.Appointment;

public interface AppointmentServiceAPI extends GenericServiceApi<Appointment, Long>{
    public List<Appointment> getByEmployeeId(long id);
    public List<Appointment> getByClientId(long id);
}
