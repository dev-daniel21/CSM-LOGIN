package com.csm.csmlogin.web;

import com.csm.csmlogin.web.exceptions.PasswordNotCorrectException;
import com.csm.csmlogin.web.exceptions.UserNotRegisteredException;
import com.csm.csmlogin.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserVerificationResponse login(@RequestParam String login, @RequestParam String password)
            throws UserNotRegisteredException, PasswordNotCorrectException {

        return loginService.authenticateUser(login, password);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserRegistrationResponse register(@RequestBody UserRegistrationRequest request) {

        return loginService.registerNewUser(request);
    }

    @DeleteMapping("/deleteUser")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@RequestParam String userLogin) {

        loginService.deleteUser(userLogin);
    }

    @PatchMapping("/updateUser")
    @ResponseStatus(HttpStatus.CREATED)
    void updateUser(@RequestParam String userLogin, @RequestBody UserRegistrationRequest request) {

        loginService.updateUser(userLogin, request);
    }

}
