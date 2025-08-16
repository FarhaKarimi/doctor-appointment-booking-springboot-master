// File: HomeController.java
package com.nadimnesar.drappointment.controller;

import com.nadimnesar.drappointment.service.SearchElement;
import com.nadimnesar.drappointment.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String rootPage(Authentication authentication,
                           HttpServletRequest request,
                           Model model) {
        SearchElement searchElement = SearchElement.getInstance();
        model.addAttribute("searchElement", searchElement); // توجه: اسم دقیق متغیر
        model.addAttribute("role", userService.getRole(authentication));
        model.addAttribute("currentURI", request.getRequestURI());
        return "index";
    }
}