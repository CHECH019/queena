package com.cdevs.queena.validations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.cdevs.queena.constants.Constants;
import com.cdevs.queena.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class UserValidations {

    public static boolean validateEmailPattern(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$"); 
        if (email != null) email =email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            return false;
        }
        return true;
    }
    
    public static Map<String,String> generateJWTToken (User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256 , Constants.API_SECRET_KEY)
            .setIssuedAt(new Date(timestamp))
            .setExpiration(new Date(timestamp+Constants.TOKEN_VALIDITY))
            .claim("userID", user.getId())
            .claim("email", user.getEmail())
            .claim("name", user.getName())
            .claim("surname", user.getSurname())
            .compact();
        Map<String, String> map = new HashMap<String,String>();
        map.put("token", token);
        return map;
    }
}
