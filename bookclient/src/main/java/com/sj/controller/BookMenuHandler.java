package com.sj.controller;

import com.sj.entity.BookVO;
import com.sj.feign.BookMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookMenu")
public class BookMenuHandler {

    @Autowired
    private BookMenuFeign bookMenuFeign;

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable String location)
    {
        return location;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public BookVO findAll(@RequestParam int page,@RequestParam int limit)
    {
        int index=(page-1)*limit;
        return bookMenuFeign.findAll(index,limit);
    }
}
