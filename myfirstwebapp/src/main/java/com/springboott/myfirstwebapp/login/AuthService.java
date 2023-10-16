package com.springboott.myfirstwebapp.login;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("dusica");
        boolean isValidPassword = password.equalsIgnoreCase("dusica123");

        return isValidUsername && isValidPassword;
    }
}
