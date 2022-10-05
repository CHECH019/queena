package com.cdevs.queena.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class Client extends User{
    
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;
}
