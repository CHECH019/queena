package com.cdevs.queena.restController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queena.commons.GenericRestController;
import com.cdevs.queena.commons.GenericServiceApi;
import com.cdevs.queena.constants.Constants;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.service.api.AppointmentServiceAPI;
import com.cdevs.queena.service.api.ClientServiceAPI;
import com.cdevs.queena.validations.UserValidations;


@RestController
@RequestMapping(value = Constants.BASE_URL + "client")
public class ClientRestController extends GenericRestController<Client,Long>{

    @Autowired
    private ClientServiceAPI service;

    @Autowired
    private AppointmentServiceAPI apService;
    
    @Override
    public GenericServiceApi<Client, Long> getService() {
        return service;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String, Object>clientMap) {
        String email = (String) clientMap.get("email");
        String pass = (String) clientMap.get("password");
        Client c = service.validateClient(email, pass);
        return new ResponseEntity<Map<String,String>>(UserValidations.generateJWTToken(c), HttpStatus.OK) ;
    }
    
    @GetMapping("/my-appointments")
    public List<Appointment> showByClientId(Model model, HttpServletRequest request){
        long id = (long) request.getAttribute("userID");
        return apService.getByClientId(id);
    }

}