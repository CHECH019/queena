package com.cdevs.queena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.model.MyService;
import com.cdevs.queena.service.api.MyServiceServiceAPI;

@Controller
@RequestMapping
public class MyServiceController {
    @Autowired
    private MyServiceServiceAPI service;

    @GetMapping("/services")
    public String show(Model model){
        model.addAttribute("services", service.getAll());
        return "services/services";
    }

    @PostMapping("/services/save")
    public String save(@ModelAttribute("service") MyService s){
        service.save(s); 
        return "redirect:/services";
    }

    @GetMapping({"/services/edit/{id}","/services/new/{id}"})
    public String showSave(@PathVariable Integer id, Model model){
        if(id != null && id != 0 ){
            model.addAttribute("service", service.get(id));
        }else{
            model.addAttribute("service", new MyService());
        }

        return "services/service-form";
    }

    @GetMapping("/services/{id}")
    public String delete(@PathVariable int id){
        service.delete(id);
        return "redirect:/services";
    }
}
