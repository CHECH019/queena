package com.cdevs.queena.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee extends User {

    @Column(nullable = false, unique = true)
    private long dni;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(
        name="employee_service",
        joinColumns = @JoinColumn(name = "employee_id"), 
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )

    private List<MyService> specializations;

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<MyService> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<MyService> specializations) {
        this.specializations = specializations;
    }
    
}
