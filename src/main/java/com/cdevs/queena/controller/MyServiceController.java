package com.cdevs.queena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdevs.queena.iServices.IMyServiceService;
import com.cdevs.queena.model.MyService;

@Controller
@RequestMapping
public class MyServiceController {
    @Autowired
    private IMyServiceService service;

    @GetMapping("/services")
    public String show(Model model){
        model.addAttribute("services", service.getList());
        return "services/services";
    }

    @GetMapping("/services/new")
    public String createService(Model model){
        model.addAttribute("service", new MyService());
        return "services/new-service";
    }

    @PostMapping("/services/save")
    public String saveService(@ModelAttribute("service") MyService s){
        service.save(s); 
        return "redirect:/services";
    }

    @GetMapping("/services/edit/{id}")
    public String editService(@PathVariable int id, Model model){
        model.addAttribute("service", service.getById(id));
        return "services/edit-service";
    }

    @PostMapping("/services/{id}")
    public String updateService(@PathVariable int id, @ModelAttribute("appointment") MyService s){
        MyService myServ = service.getById(id);
        myServ.setName(s.getName());
        myServ.setPrice(s.getPrice());
        service.update(s);
        return "redirect:/services";
    }

    @GetMapping("/services/{id}")
    public String deleteService(@PathVariable int id){
        service.delete(id);
        return "redirect:/services";
    }
}
