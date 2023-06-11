package com.example.myspringbootproject;

import com.example.myspringbootproject.domain.post.repository.BoardJdbcRepository;
import com.example.myspringbootproject.domain.post.repository.BoardRepository;
import com.example.myspringbootproject.domain.post.service.BoardService;
import com.example.myspringbootproject.domain.post.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BoardService boardService() {
        return new BoardServiceImpl(boardRepository());
    }

    @Bean
    public BoardRepository boardRepository() {
        return new BoardJdbcRepository(dataSource);
    }
}
