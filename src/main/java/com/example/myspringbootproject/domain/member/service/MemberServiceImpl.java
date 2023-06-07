package com.example.myspringbootproject.domain.member.service;

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
    public MemberEntity signUp(MemberEntity memberEntity) {
        memberRepository.save(memberEntity);
        return memberRepository.getById(memberEntity.getId());
    }

    @Override
    public Optional<MemberEntity> findUserById(int id) {
        return memberRepository.findById(id);
    }
}
