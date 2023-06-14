package com.example.myspringbootproject.domain.user.controller;

import com.example.myspringbootproject.common.response.ResponseDTO;
import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService memberService;

    @PostMapping
    public ResponseEntity<?> signUpUser(@RequestBody UserDTO userDTO){
        try{
            List<Long> userId = new ArrayList<>();
            userId.add(memberService.signUp(userDTO));

            ResponseDTO<Long> response = ResponseDTO.<Long>builder().data(userId).build();

            // TODO ResponseEntity.ok(url).body(response) 로 변환해 보기
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}