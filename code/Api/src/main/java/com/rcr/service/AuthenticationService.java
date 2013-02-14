package com.rcr.service;

import com.rcr.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: pshivana
 * Date: 2/14/13
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AuthenticationService {
    boolean isValidUser(String username, String password);

    void addCookieForUser(String username, String userCookie);

    boolean inValidToken(String userToken);

    User getUserByToken(String userToken);
}
