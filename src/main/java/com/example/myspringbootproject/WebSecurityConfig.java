package com.example.myspringbootproject;

import com.example.myspringbootproject.domain.user.service.UserDetailService;
import com.example.myspringbootproject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserDetailService userDetailService;

    // 스프링 시큐리티 기능 비활성화
    // 스프링 부트에서 제공하는 모든 정적 리소스 무시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers(toH2Console())
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    // 로그인 전에만 접근 가능
    private static final String[] AUTH_ANONYMOUS_LIST = {
        "/users/sign-up", "/users/sign-in", "/users/sign-up-form", "/users/sign-in-form",
        "/api/user"
    };

    // 인증 제외
    private static final String[] AUTH_WHITE_LIST = {
//        "/users/sign-up", "/users/sign-in", "/users/sign-up-form", "/users/sign-in-form",
//        "/api/user"
    };

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(userDetailService)
            .csrf( csrf -> csrf.disable()) // csrf 비활성화
            .authorizeHttpRequests(authorize -> // 인증, 인가 설정
                authorize
                    .requestMatchers(AUTH_WHITE_LIST).permitAll()
                    .requestMatchers(AUTH_ANONYMOUS_LIST).anonymous()
                    .anyRequest().authenticated()
            )
            .formLogin(login ->
                login // TODO: 실패시 처리
                    .loginPage("/users/sign-in")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
            )
            .logout( logout ->
                logout
                    .logoutUrl("/users/sign-out")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
            );
        return http.build();
    }

    // 패스워드 인코더 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



}