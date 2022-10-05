package com.cdevs.queena.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Employee;

@Repository
public interface EmployeeDaoApi extends CrudRepository<Employee,Long> {
    @Query(value="SELECT * FROM employee WHERE dni = :dni", nativeQuery = true)
    public Employee getEmployeeByDni(@Param("dni") long dni);

    @Query(value="SELECT * from employee WHERE email = :email", nativeQuery=true)
    public Employee getUserByEmail(@Param("email") String email);
}
