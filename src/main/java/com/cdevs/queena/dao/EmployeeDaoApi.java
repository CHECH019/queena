package com.cdevs.queena.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdevs.queena.model.Employee;
import com.cdevs.queena.model.MyService;

@Repository
public interface EmployeeDaoApi extends UserDao<Employee> {
    @Query(value="SELECT * FROM employee WHERE dni = :dni", nativeQuery = true)
    public Employee getEmployeeByDni(@Param("dni") long dni);

    @Query(value="select count(e.name), e.name, s.name from employee e join employee_service es on e.id = es.employee_id join service s on es.service_id = s.id where es.service_id = 6 or es.service_id = 5 GROUP BY e.name having count(e.name) > 1", nativeQuery=true)
    public List<Employee> getByServices(@Param("services") List<MyService> services);
}
