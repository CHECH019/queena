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

import com.cdevs.queena.constants.Constants;
import com.cdevs.queena.model.Appointment;
import com.cdevs.queena.model.Client;
import com.cdevs.queena.model.Employee;
import com.cdevs.queena.model.User;
import com.cdevs.queena.service.api.AppointmentServiceAPI;
import com.cdevs.queena.service.api.UserServiceAPI;
import com.cdevs.queena.validations.UserValidations;

@RestController
@RequestMapping(value = Constants.BASE_URL)
public class UserRestController {
    
    @Autowired
    private UserServiceAPI<Client> cliService;

    @Autowired
    private UserServiceAPI<Employee> empService;

    @Autowired
    private AppointmentServiceAPI apService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String, Object>clientMap) {
        String email = (String) clientMap.get("email");
        String pass = (String) clientMap.get("password");
        User c = null;
        try {
            c = cliService.validateUser(email, pass);   
        } catch (Exception e) {
            c = empService.validateUser(email, pass);
        }
        return new ResponseEntity<Map<String,String>>(UserValidations.generateJWTToken(c), HttpStatus.OK) ;
    }

    @GetMapping("/my-appointments")
    public List<Appointment> showByEmployeeId(HttpServletRequest request, Model model){
        long id = (long) request.getAttribute("userID");
        String role = (String) request.getAttribute("role");

        return role.equals("Employee") ? 
                apService.getByEmployeeId(id) : apService.getByClientId(id);
    }

    @PostMapping("/book")
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment a, HttpServletRequest request){
        if(request.getAttribute("role").equals("Client")){
            long id = (long) request.getAttribute("userID");
            a.setClient(cliService.get(id));
            apService.save(a);
            return new ResponseEntity<Appointment>(a, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
