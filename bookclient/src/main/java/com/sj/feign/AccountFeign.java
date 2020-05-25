package com.sj.feign;


import com.sj.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "account")
public interface AccountFeign {

    @GetMapping("/Account/login/{username}/{password}/{type}")
    public Account login(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("type")String type );
}
