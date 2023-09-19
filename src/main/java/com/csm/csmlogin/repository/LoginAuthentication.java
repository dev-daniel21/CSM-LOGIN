package com.csm.csmlogin.repository;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "login_auth")
public class LoginAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    private String login;

    private boolean isRegistered;

    public LoginAuthentication() {
    }

    public LoginAuthentication(UUID userId, String login, boolean isRegistered) {
        this.userId = userId;
        this.login = login;
        this.isRegistered = isRegistered;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}
