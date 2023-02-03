package com.cdevs.queene.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdevs.queene.client.Client;
import com.cdevs.queene.client.ClientDAO;
import com.cdevs.queene.generics.GenericServiceImpl;
import com.cdevs.queene.requestentity.AuthenticationRequest;
import com.cdevs.queene.requestentity.RegisterRequest;
import com.cdevs.queene.responseentity.AuthenticationResponse;
import com.cdevs.queene.responseentity.APIResponse;
import com.cdevs.queene.security.jwt.JwtService;
import com.cdevs.queene.utils.global.Role;

import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {

    private final UserDao dao;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final ClientDAO clientDao;

    @Override
    public CrudRepository<User, Long> getDAO() {
        return dao;
    }

    @Override
    public APIResponse authenticate(AuthenticationRequest req) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.getEmail(),
                req.getPassword() 
                )
            );

        String token;
        Map<String,Object> extraClaims = new HashMap<>();
        User acc = getByEmail(req.getEmail());

        acc.setLastLoginAt(LocalDateTime.now());
        save(acc);

        extraClaims.put("name",acc.getClient().getName());
        token = JwtService.generateToken(extraClaims, acc);
        
        return new AuthenticationResponse("User authenticated successfully", token);
    }

    @Override
    public APIResponse register(RegisterRequest req) {
        if (dao.findByEmail(req.getEmail()).isEmpty()){
            String token;
            Map<String,Object> extraClaims = new HashMap<>();
            User acc = buildUserAccount(req);
            Client client = buildClient(req, acc);
    
            save(acc);
            clientDao.save(client);
    
            extraClaims.put("name",client.getName());
            token = JwtService.generateToken(extraClaims, acc);

            return new AuthenticationResponse("User created successfully", token);
        }else{
            throw new EntityExistsException("Email already in use");
        }
    }

    @Override
    public User getByEmail(String email) {
        return dao.findByEmail(email).orElseThrow();
    }

    @Override
    public String greeting(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String name = JwtService.extractCustomClaim(token, "name");

        return name+", welcome to queene!";
    }

    private User buildUserAccount(RegisterRequest req){
        return User
            .builder()
            .email(req.getEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .createdAt(LocalDateTime.now())
            .lastLoginAt(LocalDateTime.now())
            .role(Role.CLIENT)
            .build();
    }

    private Client buildClient(RegisterRequest req, User acc){
        return Client
            .builder()
            .name(req.getName())
            .lastName(req.getLastName())
            .address(req.getAddress())
            .phoneNumber(req.getPhoneNumber())
            .account(acc)
            .build();
    }

    @Override
    public User update(User update) {
        // TODO Auto-generated method stub
        return null;
    }

}