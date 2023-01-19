package com.csm.csmlogin.repository;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "login_auth")
public class LoginAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "user_id")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String userId;

    private String login;

    private boolean isRegistered;

    public LoginAuthentication() {
    }

    public LoginAuthentication(String userId, String login, boolean isRegistered) {
        this.userId = userId;
        this.login = login;
        this.isRegistered = isRegistered;
    }

    public String getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}
