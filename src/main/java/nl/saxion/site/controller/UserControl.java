package nl.saxion.site.controller;

import nl.saxion.site.Administration.Administration;
import nl.saxion.site.model.Accessory;
import nl.saxion.site.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Controller
public class UserControl {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {

        Administration.addUser(username, password);

        return "redirect:/login";
    }

    /**
     * Handles logout requests (which is here implemented as a POST)
     * @param session
     * @return
     */
    @GetMapping(path = "/logout")
    public String postLogout(HttpSession session) {
        //Invalidate the current session, this removes it from the session
        session.invalidate();
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session, HttpServletResponse response) {

        for (User user:Administration.users ){
        if (user.getName().equals(username) && user.getPassword().equals(password)) {
            session.setAttribute("username", username);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            Calendar cal = Calendar.getInstance();

            String date = dateFormat.format(cal.getTime());


            try {
                String encodedCookieValue = URLEncoder.encode(date, "UTF-8");
                Cookie cookie1 = new Cookie("lastLoggedIn", encodedCookieValue);

                response.addCookie(cookie1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return "redirect:/";
        }}

            return "redirect:/login";

    }

    @GetMapping("/user-list")
    public String userList(Model model, HttpServletRequest request) {
        List<User> users = Administration.getUsers();
        model.addAttribute("users", users);
        return "user-list";
    }
    @GetMapping("/accessory-list")
    public String accessoryList(Model model, HttpServletRequest request) {
        List<Accessory> accessory = Administration.getAccessories();
        model.addAttribute("accessories", accessory);
        return "accessory-list";
    }


}

