package com.cdevs.queena.validations;

import java.util.regex.Pattern;


public class UserValidations <T>{

    public static boolean validateEmailPattern(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$"); 
        if (email != null) email =email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            return false;
        }
        return true;
    }
    
}
