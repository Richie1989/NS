package nl.saxion.site.controller;

import nl.saxion.site.Provider.AccessoryProvider;
import nl.saxion.site.model.Accessory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexControl {

    private final AccessoryProvider accessoryProvider;

    public IndexControl(AccessoryProvider accessoryProvider) {
        this.accessoryProvider = accessoryProvider;
    }

    @RequestMapping("/")
    public String index(HttpServletResponse response, @CookieValue(value = "counter", defaultValue = "0") int counter, Model model) {
        counter++;
        Cookie cookie = new Cookie("counter", "" + counter);


        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return "index";
    }

    private void saveCookie(String cookieName, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(3242000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
