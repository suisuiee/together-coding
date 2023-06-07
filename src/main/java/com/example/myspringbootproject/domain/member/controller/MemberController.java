package com.example.myspringbootproject.domain.member.controller;

import com.example.myspringbootproject.common.response.ResponseDTO;
import com.example.myspringbootproject.domain.member.dto.MemberDTO;
import com.example.myspringbootproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/test")
    public int testController(@RequestParam int id){
        return id;
    }

    @PostMapping
    public ResponseEntity<?> signUpMember(@RequestBody MemberDTO memberDTO){
        try{
            List<Integer> userId = new ArrayList<>();
            userId.add(memberService.signUp(memberDTO));

            ResponseDTO<Integer> response = ResponseDTO.<Integer>builder().data(userId).build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
