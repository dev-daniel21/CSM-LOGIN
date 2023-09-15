package com.csm.csmlogin.clients;

import com.csm.csmlogin.web.UserRegistrationRequest;
import com.csm.csmlogin.web.exceptions.ServiceNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersClient {

    private final RestTemplate restTemplate;

    public boolean authenticateUser(String userId, String password) {
        String url = String.format("http://localhost:8310/verify?userId=%s&userPassword=%s", userId, password);
        UserAuthenticationResponse response;
        try {
            response = restTemplate.getForObject(url, UserAuthenticationResponse.class);

        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
        System.out.println(response);
        return response != null && response.userId.equals(userId) && response.isAuthenticated;
    }

    public UUID registerNewUser(UserRegistrationRequest request) {
        String url = String.format("http://localhost:8310/registerUser?userLogin=%s&userPassword=%s",
                request.newLogin(), request.userPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        ResponseEntity<UUID> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(headers),
                    UUID.class,
                    request.newLogin(),
                    request.userPassword());
        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
        System.out.println(response.getBody());
        return response.getBody();
    }

    public void deleteUser(String userLogin) {
        String url = String.format("http://localhost:8310/deleteUser/userLogin=%s", userLogin);
        try {
            restTemplate.delete(url, HttpMethod.DELETE);
        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
    }

    public void updateUser(String userLogin, UserRegistrationRequest request) {
        String url = String.format("http://localhost:8310/updateUser=%s", userLogin);
        try {
            restTemplate.patchForObject(url, request, Void.class, userLogin);
        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
    }
}
