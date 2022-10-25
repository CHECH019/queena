package com.cdevs.queene.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "service")
public class MyService {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;
    
    private double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    List <Appointment> appointments;

    @JsonIgnore
    @ManyToMany(mappedBy = "specializations", fetch = FetchType.LAZY)
    List<Employee> employee_providers;

    @Column(columnDefinition = "varchar(255) default null")
    private String category;

    @Column(columnDefinition = "text default null")
    private String description;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    public List<Employee> getEmployee_providers() {
        return employee_providers;
    }
    public void setEmployee_providers(List<Employee> employee_providers) {
        this.employee_providers = employee_providers;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        this.description = desc;
    }
    
}