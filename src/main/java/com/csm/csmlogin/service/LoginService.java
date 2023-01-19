package com.csm.csmlogin.service;

import com.csm.csmlogin.clients.UsersClient;
import com.csm.csmlogin.web.exceptions.PasswordNotCorrectException;
import com.csm.csmlogin.web.exceptions.UserNotRegisteredException;
import com.csm.csmlogin.web.exceptions.UserAlreadyExistsException;
import com.csm.csmlogin.repository.LoginAuthentication;
import com.csm.csmlogin.repository.LoginAuthenticationJPARepository;
import com.csm.csmlogin.web.UserRegistrationRequest;
import com.csm.csmlogin.web.UserRegistrationResponse;
import com.csm.csmlogin.web.UserVerificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final LoginAuthenticationJPARepository loginAuthenticationJPARepository;

    private final UsersClient userVerificationClient;


    public UserVerificationResponse authenticateUser(String login, String password)
            throws UserNotRegisteredException, PasswordNotCorrectException {

        LoginAuthentication userAccount = loginAuthenticationJPARepository.findByLogin(login)
                .orElseThrow(() -> new UserNotRegisteredException(String.format("User %s not found", login)));

        if (userAccount.isRegistered()) {
            if (userVerificationClient.verifyUser(userAccount.getUserId(), password)) {
                return new UserVerificationResponse(String.format("%s authenticated", login));
            } else {
                throw new PasswordNotCorrectException(String.format("Wrong password provided for user: %s", login));
            }
        } else {
            throw new UserNotRegisteredException(String.format("user %s not registered", login));
        }
    }

    public UserRegistrationResponse registerNewUser(UserRegistrationRequest request) throws UserAlreadyExistsException{
        LoginAuthentication userAccount = loginAuthenticationJPARepository.findByLogin(request.newLogin())
                .orElse(null);

        if (Objects.nonNull(userAccount)) {
            throw new UserAlreadyExistsException("User login is already taken");
        } else {
            UUID newUserId = userVerificationClient.registerNewUser(request);
            LoginAuthentication newUserAccount = new LoginAuthentication(newUserId.toString(), request.newLogin(), true);
            loginAuthenticationJPARepository.save(newUserAccount);
            return new UserRegistrationResponse(String.format("New user %s registered", request.newLogin()));
        }
    }

    public void deleteUser(String userLogin) {
        if (loginAuthenticationJPARepository.existsByLogin(userLogin)) {
            loginAuthenticationJPARepository.deleteByLogin(userLogin);
            userVerificationClient.deleteUser(userLogin);
        }
    }

    @Transactional
    public void updateUser(String userLogin, UserRegistrationRequest request) {
        if (loginAuthenticationJPARepository.existsByLogin(userLogin)) {
            loginAuthenticationJPARepository.updateUser(userLogin, request.newLogin());
            userVerificationClient.updateUser(userLogin, request);
        }
    }
}
