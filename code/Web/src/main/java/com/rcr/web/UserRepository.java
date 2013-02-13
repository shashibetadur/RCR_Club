package com.rcr.web;

import com.rcr.web.model.Role;
import com.rcr.web.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Component
@Qualifier("userRepository")
public class UserRepository {

    public Map<String, User> userMap = new HashMap<String, User>();
    public static Map<String, User> userCookieMap = new HashMap<String, User>();

    public UserRepository() {
        userMap.put("user", new User("user", "user", Role.OPERATOR));
        userMap.put("admin", new User("admin", "admin", Role.ADMIN));
    }

    public boolean isValidUser(String username, String password) {
        return userMap.containsKey(username.toLowerCase()) && userMap.get(username.toLowerCase()).getPassword().equals(password);
    }

    public void addCookieForUser(String username, String userCookie) {
        userCookieMap.put(userCookie, userMap.get(username));
    }

    public boolean inValidToken(String userToken) {
        return isEmpty(userToken) || !userCookieMap.containsKey(userToken);
    }

    public User getUserByToken(String userToken) {
        return userCookieMap.get(userToken);
    }
}
