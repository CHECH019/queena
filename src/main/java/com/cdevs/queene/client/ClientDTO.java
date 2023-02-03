package com.cdevs.queene.client;

import com.cdevs.queene.responseentity.APIResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClientDTO implements APIResponse{
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;
}
