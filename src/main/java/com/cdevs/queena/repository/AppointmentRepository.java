package com.cdevs.queena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Long>{
    @Query(value="SELECT * FROM appointment WHERE employee_id = :employee_id",
    nativeQuery=true)
    List<Appointment> findByEmployeeId(@Param("employee_id") long id);
    @Query(value="SELECT * FROM appointment WHERE client_id = :employee_id",
    nativeQuery=true)
    List<Appointment> findByClientId(@Param("employee_id") long id);
}
