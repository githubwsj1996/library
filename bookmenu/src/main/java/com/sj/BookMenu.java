package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sj.repository")
public class BookMenu {
    public static void main(String[] args) {
        SpringApplication.run(BookMenu.class,args);
    }
}
