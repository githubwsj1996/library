package com.sj.controller;

import com.sj.entity.Account;
import com.sj.repository.BookAdminRepository;
import com.sj.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class AccountHandler {

    @Autowired
    private BookAdminRepository bookAdminRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/login/{username}/{password}/{type}")
    public Account login(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("type") String type)
    {
        Account account=null;
        switch (type)
        {
            case "reader":
                account=readerRepository.login(username,password);
                break;
            case "bookAdmin":
                account=bookAdminRepository.login(username,password);
                break;
        }
        return  account;
    }
}
