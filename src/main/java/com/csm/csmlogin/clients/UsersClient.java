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


@Service
@Slf4j
@RequiredArgsConstructor
public class UsersClient {

    private final RestTemplate restTemplate;

    public boolean authenticateUser(String userId, String password) {
        String url = String.format("http://localhost:8310/users/auth?userId=%s&userPassword=%s", userId, password);
        UserAuthenticationResponse response;
        try {
            response = restTemplate.getForObject(url, UserAuthenticationResponse.class);

        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
        System.out.println(response);
        return response != null && response.userId.equals(userId) && response.isAuthenticated;
    }

    public String registerNewUser(UserRegistrationRequest request) {
        String url = String.format("http://localhost:8310/users/registerUser?userLogin=%s&userPassword=%s",
                request.newLogin(), request.userPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(headers),
                    String.class,
                    request.newLogin(),
                    request.userPassword());
            log.info(response.getBody());
        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
        return response.getBody();
    }

    public void updateUser(String userLogin, UserRegistrationRequest request) {
        String url = String.format("http://localhost:8310/users/updateUser?userLogin=%s", userLogin);
        try {
            restTemplate.put(url, request, Void.class, userLogin);
        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
    }

    public void deleteUser(String userLogin) {
        String url = String.format("http://localhost:8310/users/deleteUser?userLogin=%s", userLogin);
        try {
            restTemplate.delete(url, HttpMethod.DELETE);
        } catch (RestClientException exception) {
            throw new ServiceNotAvailableException("Authentication service not available");
        }
    }
}
