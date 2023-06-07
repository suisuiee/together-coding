package com.example.myspringbootproject.domain.member.service;

import com.example.myspringbootproject.domain.member.dto.MemberDTO;
import com.example.myspringbootproject.domain.member.model.MemberEntity;

import java.util.Optional;

public interface MemberService {
    int signUp(MemberDTO memberDTO);
    Optional<MemberEntity> findUserById(int id);
}
