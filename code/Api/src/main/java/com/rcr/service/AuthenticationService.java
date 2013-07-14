package com.rcr.service;

import com.rcr.domain.User;

public interface AuthenticationService {
    boolean isValidUser(String username, String password);

    void addCookieForUser(String username, String userCookie);

    boolean inValidToken(String userToken);

    User getUserByToken(String userToken);
}
