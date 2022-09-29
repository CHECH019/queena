package com.cdevs.queena.iServices;

import java.util.List;

import com.cdevs.queena.model.Appointment;

public interface IAppointmentService {
    public List<Appointment> getList();
    public List<Appointment> getByEmployeeId(long id);
    public List<Appointment> getByClientId(long id);
    public Appointment getById(long id);
    public Appointment save(Appointment a);
    public void delete(long id);
    public Appointment update(Appointment a);
}
