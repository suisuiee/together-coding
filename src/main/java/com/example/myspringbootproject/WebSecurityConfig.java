package com.example.myspringbootproject;

import com.example.myspringbootproject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserService userService;

    // 스프링 시큐리티 기능 비활성화
    // 스프링 부트에서 제공하는 모든 정적 리소스 무시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers(toH2Console())
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성




}