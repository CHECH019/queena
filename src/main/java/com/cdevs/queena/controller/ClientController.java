package com.cdevs.queena.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.ClientServiceAPI;

@Controller
@RequestMapping
public class ClientController {
    @Autowired
    private ClientServiceAPI service;

    @GetMapping("/clients")
    public String show(Model model){
        model.addAttribute("clients", service.getAll());
        return "/clients/clients";
    }

    @PostMapping("/clients/save")
    public String save(@ModelAttribute("client") Client client){
        service.save(client); 
        return "redirect:/clients";
    }

    @GetMapping({"/clients/edit/{id}","/clients/new/{id}"})
    public String showSave(@PathVariable Long id, Model model){
        if(id != null && id != 0){
            model.addAttribute("client", service.get(id));
        }else{
            model.addAttribute("client", new Client());
        }
        return "/clients/client-form";
    }

    @GetMapping("/clients/{id}")
    public String delete(@PathVariable long id){
        service.delete(id);
        return "redirect:/clients";
    }
}
