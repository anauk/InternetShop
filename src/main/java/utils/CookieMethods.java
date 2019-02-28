package utils;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import service.UserService;
import shopping.exception.ElementNotFoundInDbException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieMethods {
    private String COOKIES_NAME;
    private final UserService us;

    public CookieMethods(String cookie, UserService us) {
        this.COOKIES_NAME = cookie;
        this.us = us;
    }
    public String getValue(HttpServletRequest req) throws CookieUnavailableException {
        Cookie[] cookies = req.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw new CookieUnavailableException();
        }
        String value = Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(COOKIES_NAME))
                .findFirst().get().getValue();
        return value;
    }

    public boolean cookieMatch(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        final boolean[] result = {false};
        if (cookies == null || cookies.length == 0) {
            return false;
        }
        Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(COOKIES_NAME))
                .findFirst()
                .ifPresent(c -> {
                    try {
                        us.getUser(Integer.parseInt(c.getValue()));
                        result[0] = true;
                    } catch (ElementNotFoundInDbException | NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                });
        return result[0];
    }


}
