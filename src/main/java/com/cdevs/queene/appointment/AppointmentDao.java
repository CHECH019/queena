package com.cdevs.queene.appointment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentDao extends CrudRepository<Appointment,Long>{
    @Query("SELECT a FROM Appointment a WHERE a.employee.id= ?1")
    Optional<List<Appointment>> findByEmployeeId(Long id);

    @Query("SELECT a FROM Appointment a WHERE a.client.id = ?1")
    Optional<List<Appointment>> findByClientId(Long id);
}
