package com.example.myspringbootproject.domain.member.repository;

import com.example.myspringbootproject.domain.member.dto.MemberDTO;
import com.example.myspringbootproject.domain.member.model.MemberEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    @Override
    Optional<MemberEntity> findById(Integer integer);

    @Override
    List<MemberEntity> findAllById(Iterable<Integer> integers);

}
