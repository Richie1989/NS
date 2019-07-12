package nl.saxion.site.controller;

import nl.saxion.site.Administration.Administration;
import nl.saxion.site.model.Accessory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/accessory")
public class AccessoriesControl {

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String doAdd(HttpServletRequest request,
                        @RequestParam("stock") int id,
                        @RequestParam("name") String name,
                        @RequestParam("category") String category,
                        @RequestParam("stock") int stock,
                        @RequestParam("price") int price,
                        @RequestParam("description") String description, HttpSession session) {
        Administration.addAccessory(id,name,stock,price,category,description,session.getAttribute("username").toString());
        return "redirect:/";
    }

    @GetMapping("/single/{id}")
    public String single(@PathVariable int id, Model model) {
        model.addAttribute("accessory", Administration.accessories.get(id));
        return "single";
    }

//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable int id, Model model) {
//        model.addAttribute("accessory", Administration.accessories.get(id));
//        Administration.removeAccessory(id);
//        return "redirect:/";
//}

    @PostMapping("/single/{id}")
    public String updateSingle(HttpServletRequest request,
                               @PathVariable int id,
                               @RequestParam("name") String name,
                               @RequestParam("category") String category,
                               @RequestParam("stock") int stock,
                               @RequestParam("price") int price,
                               @RequestParam("description") String description,HttpSession session) {


        Administration.accessories.set(id, new Accessory(id, name, stock, price, category, description, session.getAttribute("username").toString()));
        return "redirect:/";
    }

    @RequestMapping("/category")
    public String category() {
        return "category";
    }

    @RequestMapping("/search")
    public String search(HttpServletRequest request, @RequestParam String n, Model model) {
        model.addAttribute("n", n.toLowerCase());
        model.addAttribute("accessories",Administration.accessories);
        return "search";
    }


}

