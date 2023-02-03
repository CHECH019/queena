package com.cdevs.queene.responseentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends BasicResponse{
    private String token;

    public AuthenticationResponse(String message, String token) {
        super(message);
        this.token = token;
    }

}
