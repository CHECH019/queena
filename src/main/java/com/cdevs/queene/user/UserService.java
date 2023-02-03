package com.cdevs.queene.user;

import com.cdevs.queene.generics.GenericService;
import com.cdevs.queene.requestentity.AuthenticationRequest;
import com.cdevs.queene.requestentity.RegisterRequest;
import com.cdevs.queene.responseentity.APIResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends GenericService<User,Long>{
    
    /**
     * @param email 
     * @param pass
     * @return returns null if password is not correct or email doesn't exist
     */
    public APIResponse authenticate(AuthenticationRequest req);
    public APIResponse register(RegisterRequest req);
    public User getByEmail(String email);
    public String greeting(HttpServletRequest request);
}
