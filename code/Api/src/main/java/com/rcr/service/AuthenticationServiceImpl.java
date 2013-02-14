package com.rcr.service;


import com.rcr.domain.Role;
import com.rcr.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    public Map<String, User> userMap = new HashMap<String, User>();
    public static Map<String, User> userCookieMap = new HashMap<String, User>();

    public AuthenticationServiceImpl() {
        userMap.put("user", new User("user", "user", Role.OPERATOR));
        userMap.put("admin", new User("admin", "admin", Role.ADMIN));
    }

    @Override
    public boolean isValidUser(String username, String password) {
        return userMap.containsKey(username.toLowerCase()) && userMap.get(username.toLowerCase()).getPassword().equals(password);
    }

    @Override
    public void addCookieForUser(String username, String userCookie) {
        userCookieMap.put(userCookie, userMap.get(username));
    }

    @Override
    public boolean inValidToken(String userToken) {
        return isEmpty(userToken) || !userCookieMap.containsKey(userToken);
    }

    @Override
    public User getUserByToken(String userToken) {
        return userCookieMap.get(userToken);
    }
}
