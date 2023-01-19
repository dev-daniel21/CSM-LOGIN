package com.csm.csmlogin.clients;

import com.csm.csmlogin.web.UserRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class UsersClient {

    private final RestTemplate restTemplate;

    public UsersClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyUser(String userId, String password) {

        String url = String.format("http://localhost:8310/verify?userId=%s&userPassword=%s", userId, password);

        UserAuthenticationResponse response = restTemplate.getForObject(url, UserAuthenticationResponse.class);
        System.out.println(response);
        return response != null && response.userId.equals(userId) && response.isAuthenticated;
    }

    public UUID registerNewUser(UserRegistrationRequest request) {
        String url = String.format("http://localhost:8310/registerUser?userLogin=%s&userPassword=%s",
                request.newLogin(), request.userPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        ResponseEntity<UUID> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(headers),
                UUID.class,
                request.newLogin(),
                request.userPassword());

        System.out.println(response.getBody());
        return response.getBody();
    }

    public void deleteUser(String userLogin) {
        String url = String.format("http://localhost:8310/deleteUser/userLogin=%s", userLogin);

        restTemplate.delete(url, HttpMethod.DELETE);
    }

    public void updateUser(String userLogin, UserRegistrationRequest request) {
        String url = String.format("http://localhost:8310/updateUser=%s", userLogin);

        restTemplate.patchForObject(url, request, Void.class, userLogin);

    }
}
