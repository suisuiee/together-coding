package com.example.myspringbootproject.domain.member.controller;

import com.example.myspringbootproject.common.response.ResponseDTO;
import com.example.myspringbootproject.domain.member.dto.MemberDTO;
import com.example.myspringbootproject.domain.member.model.MemberEntity;
import com.example.myspringbootproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

            List<MemberEntity> entityList = new ArrayList<>();
            // MemberDTO -> MemberEntity
            MemberEntity memberEntity = memberService.signUp(MemberDTO.toEntity(memberDTO));
            entityList.add(memberEntity);

            // MemberEntity -> MemberDTO
            List<MemberDTO> memberDTOList = entityList.stream().map(MemberDTO::new).collect(Collectors.toList());

            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(memberDTOList).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
