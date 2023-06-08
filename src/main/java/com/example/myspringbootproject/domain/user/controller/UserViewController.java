package com.example.myspringbootproject.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserViewController {

    @GetMapping("/users/sign-up")
    public String signUpForm(Model model) {
        return "users/sign-up-form";
    }

    @PostMapping("/users/sign-up-form")
    public String signUpUser(Model model) {
        model.addAttribute("headTitle", "회원가입");
        return "redirect:/";
    }
}
