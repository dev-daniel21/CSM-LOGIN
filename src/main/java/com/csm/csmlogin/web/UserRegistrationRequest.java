package com.csm.csmlogin.web;


public record UserRegistrationRequest(
        String newLogin,
        String userPassword,
        String firstName,
        String lastName
) {

}
