package com.sj.controller;

import com.sj.entity.Account;
import com.sj.entity.BookAdmin;
import com.sj.entity.Reader;
import com.sj.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session)
    {
        Account account=accountFeign.login(username,password,type);
        String result=null;
        if(account==null)
        {
            result="redirect:/login.html";
        }
        else{
            switch (type){
                case "reader":
                    Reader reader=new Reader();
                    reader.setId(account.getId());
                    reader.setName(account.getName());
                    reader.setUsername(account.getUsername());
                    session.setAttribute("reader",reader);
                    result="redirect:/bookMenu/redirect/bookMenu";
                    break;
                case "bookAdmin":
                    BookAdmin bookAdmin=new BookAdmin();
                    bookAdmin.setId(account.getId());
                    bookAdmin.setUsername(account.getUsername());
                    session.setAttribute("bookAdmin",bookAdmin);
                    result="redirect:/bookMenu/redirect/main";
                    break;
            }
        }
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/login.html";
    }
}
