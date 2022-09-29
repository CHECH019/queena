package com.cdevs.queena.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.iServices.IClientService;
import com.cdevs.queena.model.Client;

@Controller
@RequestMapping
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping("/clients")
    public String show(Model model){
        model.addAttribute("clients", service.getList());
        return "/clients/clients";
    }

    @GetMapping("/clients/new")
    public String createEmployee(Model model){
        model.addAttribute("client", new Client());
        return "/clients/new-client";
    }

    @PostMapping("/clients/save")
    public String saveClient(@ModelAttribute("client") Client client){
        service.save(client); 
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String editClient(@PathVariable long id, Model model){
        model.addAttribute("client", service.getById(id));
        return "/clients/edit-client";
    }

    @PostMapping("/clients/{id}")
    public String updateClient(@PathVariable long id, @ModelAttribute("client") Client client){
        Client c = service.getById(id);
        c.setName(client.getName());
        c.setSurname(client.getSurname());
        c.setPhoneNumber(client.getPhoneNumber());
        c.setPassword(client.getPassword());
        c.setAddress(client.getAddress());

        service.update(c);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{id}")
    public String deleteClient(@PathVariable long id){
        service.delete(id);
        return "redirect:/clients";
    }
}
