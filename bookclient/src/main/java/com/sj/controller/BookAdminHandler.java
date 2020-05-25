package com.sj.controller;

import com.sj.entity.Book;
import com.sj.entity.Reader;
import com.sj.entity.ReaderVO;
import com.sj.feign.BookAdminFeign;
import com.sj.feign.BookMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bookAdmin")
public class BookAdminHandler {

    @Autowired
    private BookMenuFeign bookMenuFeign;

    @Autowired
    private BookAdminFeign bookAdminFeign;

    //对图书进行CRUD
    @GetMapping("/findTypes")
    @ResponseBody
    public ModelAndView findTypes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("types", bookMenuFeign.findAllBookCase());
        modelAndView.setViewName("bookMenu_add");
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Book book) {
        bookMenuFeign.save(book);
        return "redirect:/bookMenu/redirect/bookMenu_manage";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookMenuFeign.deleteBook(id);
        return "redirect:/bookMenu/redirect/bookMenu_manage";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bookMenuFeign.findById(id));
        modelAndView.addObject("types", bookMenuFeign.findAllBookCase());
        modelAndView.setViewName("bookMenu_edit");
        return modelAndView;
    }

    @PostMapping("/updateBook")
    public String updateBook(Book book) {
        bookMenuFeign.updateBook(book);
        return "redirect:/bookMenu/redirect/bookMenu_manage";
    }

    //对用户CRUD
    @GetMapping("/findAllReader")
    @ResponseBody
    public ReaderVO findAll(@RequestParam int page,@RequestParam int limit){
        int index=(page-1)*limit;
        return  bookAdminFeign.findAll(index,limit);
    }

    @PostMapping("/saveReader")
    public String saveReader(Reader reader)
    {
        bookAdminFeign.saveReader(reader);
        return "redirect:/bookMenu/redirect/reader_manage";
    }

    @GetMapping("/deleteReader/{id}")
    public String deleteReader(@PathVariable("id")int id)
    {
        bookAdminFeign.deleteReader(id);
        return "redirect:/bookMenu/redirect/reader_manage";
    }

    @GetMapping("findByReaderId/{id}")
    public ModelAndView findByReaderId(@PathVariable("id") int id)
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("reader",bookAdminFeign.findById(id));
        modelAndView.setViewName("reader_edit");
        return modelAndView;
    }

    @PostMapping("/updateReader")
    public String updateReader(Reader reader)
    {
        bookAdminFeign.updateReader(reader);
        return "redirect:/bookMenu/redirect/reader_manage";
    }
}