package com.cdevs.queene.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdevs.queene.config.Constants;
import com.cdevs.queene.model.AuthenticationRequest;
import com.cdevs.queene.model.AuthenticationResponse;
import com.cdevs.queene.model.Client;
import com.cdevs.queene.model.RegisterRequest;
import com.cdevs.queene.model.ResponseStatus;
import com.cdevs.queene.service.api.ClientServiceAPI;
import com.cdevs.queene.service.api.UserAccountServiceAPI;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = Constants.BASE_URL)
public class AuthController {

    @Autowired
    private UserAccountServiceAPI service;

    @Autowired
    private ClientServiceAPI cService;
    
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
        @RequestBody RegisterRequest req
    ){
        AuthenticationResponse response = service.register(req);
        if(response.getStatus().equals(ResponseStatus.FAILED)){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping("/clients")
    public List<Client> clients(){
        List<Client> c = cService.getAll();
        return c;
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

    // @PostMapping("save/client")
    // public ResponseEntity<Client> save(@RequestBody Client entity){
    //     // entity.setUserRole(Constants.ROLE_CLIENT);
    //     Client e = (Client) employeeService.save(entity); 
    //     return new ResponseEntity<>(e, HttpStatus.OK);
    // }

    // @PostMapping("save/employee")
    // public ResponseEntity<Employee> saveEmp(@RequestBody Employee entity){
    //     // entity.setUserRole(Constants.ROLE_EMPLOYEE);
    //     Employee e = (Employee) employeeService.save(entity); 
    //     return new ResponseEntity<>(e, HttpStatus.OK);
    // }
    // @GetMapping("/all-clients")
    // public ResponseEntity<List<UserProfile>> getAllClients(Model model, HttpServletRequest request){
    //     String role = (String) request.getAttribute("role");
    //     if(role.equals(Constants.ROLE_ADMIN)){
    //         return new ResponseEntity<>(employeeService.getByRole("Client"),HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    // }
    // @GetMapping("/all-employees")
    // public ResponseEntity<List<UserProfile>> getAllEmp(Model model, HttpServletRequest request){
    //     String role = (String) request.getAttribute("role");
    //     if(role.equals(Constants.ROLE_ADMIN)){
    //         return new ResponseEntity<>(employeeService.getByRole("Employee"),HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    // }

    // @GetMapping("/employees-by-services")
    // public ResponseEntity<List<UserProfile>> getByServices(@RequestBody List<MyService> services){
    //     services.forEach(serv -> System.out.println(serv.getId()));
    //     return new ResponseEntity<>(employeeService.getbyServicesList(services),HttpStatus.OK);
    // }
    // @Override
    // public GenericServiceApi<UserProfile, Long> getService() {
    //     return employeeService;
    // }
}