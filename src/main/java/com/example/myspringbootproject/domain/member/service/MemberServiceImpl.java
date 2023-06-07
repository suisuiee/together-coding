package com.example.myspringbootproject.domain.member.service;

import com.example.myspringbootproject.domain.member.dto.MemberDTO;
import com.example.myspringbootproject.domain.member.model.MemberEntity;
import com.example.myspringbootproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;


    @Override
    public int signUp(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberDTO.toEntity(memberDTO);
        // TODO : 여기서 유효성 검사 하기 (id, name unique 체크)
        return memberRepository.save(memberEntity).getId();
    }

    @Override
    public Optional<MemberEntity> findUserById(int id) {
        return memberRepository.findById(id);
    }
}
