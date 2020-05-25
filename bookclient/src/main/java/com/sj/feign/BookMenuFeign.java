package com.sj.feign;

import com.sj.entity.Book;
import com.sj.entity.BookCase;
import com.sj.entity.BookVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "bookmenu")
public interface BookMenuFeign {

    @GetMapping("/bookMenu/findAll/{index}/{limit}")
    public BookVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @GetMapping("/bookMenu/count")
    public int count();

    @PutMapping("/bookMenu/updateAbled/{id}")
    public void updateAbled(@PathVariable("id")int id);

    @PutMapping("/bookMenu/updateAbledById/{id}")
    public void updateAbledById(@PathVariable("id")int id);

    @GetMapping("/bookMenu/findAllBookCase")
    public List<BookCase> findAllBookCase();

    @PostMapping("/bookMenu/save")
    public void save(@RequestBody Book book);

    @DeleteMapping("/bookMenu/deleteById/{id}")
    public void deleteBook (@PathVariable("id")int id);

    @GetMapping("/bookMenu/findById/{id}")
    public Book findById(@PathVariable("id")int id);

    @PutMapping("/bookMenu/updateBook")
    public void updateBook(Book book);

}
