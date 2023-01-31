package com.cdevs.queene.auth;

import com.cdevs.queene.utils.global.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private ResponseStatus status;
    private String message;
    private String token;

}
