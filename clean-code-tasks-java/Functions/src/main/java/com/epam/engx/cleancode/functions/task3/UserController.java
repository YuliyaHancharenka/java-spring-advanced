package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        if (userAuthenticator.login(userName, password) == null)
            generateFailLoginResponse();
        else
            generateSuccessLoginResponse(userName);
    }
}
