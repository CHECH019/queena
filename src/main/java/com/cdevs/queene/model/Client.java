package com.cdevs.queene.model;


import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("1")
public class Client extends User{
    
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;
}
