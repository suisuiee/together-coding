package com.example.myspringbootproject.domain.user.repository;

import com.example.myspringbootproject.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Override
    Optional<UserEntity> findById(Integer integer);

    @Override
    List<UserEntity> findAllById(Iterable<Integer> integers);

}
