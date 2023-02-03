package com.cdevs.queene.responseentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicResponse implements APIResponse{
    private String message;

    public BasicResponse(String message) {
        this.message = message;
    }
    
}
