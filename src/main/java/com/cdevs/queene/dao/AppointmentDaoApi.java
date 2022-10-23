package com.cdevs.queene.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdevs.queene.model.Appointment;

@Repository
public interface AppointmentDaoApi extends CrudRepository<Appointment,Long>{
    @Query(value="SELECT a FROM Appointment a WHERE employee_id = ?1")
    List<Appointment> findByEmployeeId(long id);

    @Query(value="SELECT a FROM Appointment a WHERE client_id = ?1")
    List<Appointment> findByClientId(long id);
}
