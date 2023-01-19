package com.csm.csmlogin.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder
@Jacksonized
public class UserVerificationResponse {

    @JsonProperty
    String loginResponse;

}