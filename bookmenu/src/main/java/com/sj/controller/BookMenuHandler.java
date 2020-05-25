package com.sj.controller;

import com.sj.entity.Book;
import com.sj.entity.BookCase;
import com.sj.entity.BookVO;
import com.sj.repository.BookCaseRepository;
import com.sj.repository.BookMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookMenu")
public class BookMenuHandler {

    @Autowired
    private BookMenuRepository bookMenuRepository;

    @Autowired
    private BookCaseRepository bookCaseRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public BookVO findAll(@PathVariable("index") int index, @PathVariable int limit)
    {
        return new BookVO(0,"",bookMenuRepository.count(),bookMenuRepository.findAll(index,limit));
    }

    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id") int id)
    {
        return  bookMenuRepository.findById(id);
    }

    @PostMapping("/save")
    public void save( @RequestBody Book book)
    {
        bookMenuRepository.save(book);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id")int id)
    {
        bookMenuRepository.deleteById(id);
    }

    @PutMapping("/updateBook")
    public void UpdateBook(@RequestBody Book book)
    {
        bookMenuRepository.updateBook(book);
    }

    @GetMapping("/findAllBookCase")
    public List<BookCase> findAll()
    {
       return bookCaseRepository.findAll();
    }

    @PutMapping("/updateAbled/{id}")
    public  void udpateAbled(@PathVariable("id")int id)
    {
        bookMenuRepository.updateAbled(id);
    }

    @PutMapping("/updateAbledById/{id}")
    public void updateAbledById(@PathVariable("id") int id)
    {
        bookMenuRepository.updateAbledById(id);
    }


}
