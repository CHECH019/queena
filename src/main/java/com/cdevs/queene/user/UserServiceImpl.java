package com.cdevs.queene.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdevs.queene.auth.AuthenticationRequest;
import com.cdevs.queene.auth.AuthenticationResponse;
import com.cdevs.queene.auth.RegisterRequest;
import com.cdevs.queene.client.Client;
import com.cdevs.queene.client.ClientDao;
import com.cdevs.queene.generics.GenericServiceImpl;
import com.cdevs.queene.security.jwt.JwtService;
import com.cdevs.queene.utils.global.ResponseStatus;
import com.cdevs.queene.utils.global.Role;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {

    private final UserDao dao;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final ClientDao clientDao;

    @Override
    public CrudRepository<User, Long> getDAO() {
        return dao;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        try{
            authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.getEmail(),
                req.getPassword() 
                )
            );

            String token;
            Map<String,Object> extraClaims = new HashMap<>();
            User acc = getByEmail(req.getEmail()).orElse(null);

            acc.setLastLoginAt(LocalDateTime.now());
            save(acc);

            extraClaims.put("name",acc.getClient().getName());
            token = JwtService.generateToken(extraClaims, acc);

            return buildSuccessAuthResponse("User authenticated successfully", token);

        }catch(BadCredentialsException e){
            return buildFailedAuthResponse("Invalid login credentials");
        }
    }

    @Override
    public AuthenticationResponse register(RegisterRequest req) {
        if (getByEmail(req.getEmail()).isEmpty()){
            String token;
            Map<String,Object> extraClaims = new HashMap<>();
            User acc = buildUserAccount(req);
            Client client = buildClient(req, acc);
    
            save(acc);
            clientDao.save(client);
    
            extraClaims.put("name",client.getName());
            token = JwtService.generateToken(extraClaims, acc);

            return buildSuccessAuthResponse("User created successfully", token);
        }

        return buildFailedAuthResponse("Email already in use");   
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return dao.findByEmail(email);
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

    private AuthenticationResponse buildSuccessAuthResponse(String msg, String token){
        return AuthenticationResponse.builder()
            .status(ResponseStatus.SUCCESS)
            .message(msg)
            .token(token)
            .build();
    }

    private AuthenticationResponse buildFailedAuthResponse(String msg){
        return AuthenticationResponse.builder()
            .status(ResponseStatus.FAILED)
            .message(msg)
            .build();
    }

}