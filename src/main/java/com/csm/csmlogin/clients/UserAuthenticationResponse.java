package com.csm.csmlogin.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserAuthenticationResponse implements Serializable {

    @JsonProperty
    String userId;
    @JsonProperty
    boolean isAuthenticated;

}
