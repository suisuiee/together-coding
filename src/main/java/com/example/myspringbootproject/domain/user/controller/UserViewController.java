package com.example.myspringbootproject.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserViewController {

    @RequestMapping("/users/sign-up")
    public String signUpForm(Model model) {
        return "users/sign-up-form";
    }

    @PostMapping("/users/sign-up-form")
    public String signUpUser(Model model) {
        model.addAttribute("headTitle", "회원가입");
        return "redirect:/";
    }

    // TODO : PostMapping 으로 바꾸면 오류남
    @GetMapping("/users/sign-in")
    public String signInForm(Model model){
        model.addAttribute("headTitle", "로그인");
        return "users/sign-in";
    }

    @GetMapping("/users/sign-out")
    public String signOut(HttpServletRequest req, HttpServletResponse res){
        new SecurityContextLogoutHandler()
                .logout(
                    req,
                    res,
                    SecurityContextHolder.getContext().getAuthentication()
                );
        return "redirect:/";
    }
}
