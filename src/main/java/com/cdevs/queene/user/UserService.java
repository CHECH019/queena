package com.cdevs.queene.user;

import java.util.Optional;

import com.cdevs.queene.auth.AuthenticationRequest;
import com.cdevs.queene.auth.AuthenticationResponse;
import com.cdevs.queene.auth.RegisterRequest;
import com.cdevs.queene.generics.GenericService;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends GenericService<User,Long>{
    
    /**
     * @param email 
     * @param pass
     * @return returns null if password is not correct or email doesn't exist
     */
    public AuthenticationResponse authenticate(AuthenticationRequest req);
    public AuthenticationResponse register(RegisterRequest req);
    public Optional<User> getByEmail(String email);
    public String greeting(HttpServletRequest request);
}
