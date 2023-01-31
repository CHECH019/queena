package com.cdevs.queene.service.api;

import java.util.Optional;

import com.cdevs.queene.model.AuthenticationRequest;
import com.cdevs.queene.model.AuthenticationResponse;
import com.cdevs.queene.model.RegisterRequest;
import com.cdevs.queene.model.UserAccount;

import jakarta.servlet.http.HttpServletRequest;

public interface UserAccountServiceAPI extends GenericServiceApi<UserAccount,Long>{
    
    /**
     * @param email 
     * @param pass
     * @return returns null if password is not correct or email doesn't exist
     */
    public AuthenticationResponse authenticate(AuthenticationRequest req);
    public AuthenticationResponse register(RegisterRequest req);
    public Optional<UserAccount> getByEmail(String email);
    public String greeting(HttpServletRequest request);
}
