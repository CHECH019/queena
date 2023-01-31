package com.cdevs.queene.user;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queene.auth.AuthenticationRequest;
import com.cdevs.queene.auth.AuthenticationResponse;
import com.cdevs.queene.auth.RegisterRequest;
import com.cdevs.queene.utils.global.Constants;
import com.cdevs.queene.utils.global.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(Constants.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    
    @GetMapping("/")
    public String queene(HttpServletRequest request){
        return service.greeting(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody AuthenticationRequest request
    ) {            
        AuthenticationResponse response = service.authenticate(request);
        if(response.getStatus().equals(ResponseStatus.FAILED)){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
        @RequestBody RegisterRequest req
    ){
        AuthenticationResponse response = service.register(req);
        if(response.getStatus().equals(ResponseStatus.FAILED)){
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(response);
    }

    

    // @PostMapping("/book")
    // public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment a, HttpServletRequest request){
    //     if(!request.getAttribute("role").equals(Constants.ROLE_CLIENT)){
    //         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    //     }
    //     if(a.getEmployee() == null){
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     }
        
    //     UserProfile employee = employeeService.get(a.getEmployee().getId());
    //     // if(employee != null && !employee.getUserRole().equals(Constants.ROLE_EMPLOYEE)){
    //     //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     // }
        
    //     long id = (long) request.getAttribute("userID");
    //     UserProfile client = employeeService.get(id);
    //     List<MyService> myServices = new ArrayList<>();
    //     a.getServices().forEach(serv -> myServices.add(myServService.get(serv.getId())));
    //     a.setClient((Client) client);
    //     a.setEmployee((Employee) employee);
    //     a.setServices(myServices);
    //     apService.save(a);
    //     return new ResponseEntity<Appointment>(a, HttpStatus.OK);    
        
    // }


    // @GetMapping("/employees-by-services")
    // public ResponseEntity<List<UserProfile>> getByServices(@RequestBody List<MyService> services){
    //     services.forEach(serv -> System.out.println(serv.getId()));
    //     return new ResponseEntity<>(employeeService.getbyServicesList(services),HttpStatus.OK);
    // }
    
}