package com.sj.controller;

import com.sj.entity.Reader;
import com.sj.entity.ReaderVO;
import com.sj.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
public class ReaderHandler {

    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public ReaderVO findAll(@PathVariable("index")int index,@PathVariable("limit")int limit){
        return new ReaderVO(0,"",readerRepository.countReader(),readerRepository.findAll(index,limit));
    }

    @GetMapping("/findById/{id}")
    public Reader findById(@PathVariable("id") int id)
    {
        return  readerRepository.findById(id);
    }

    @PostMapping("/saveReader")
    public void saveReader(@RequestBody Reader reader)
    {
        readerRepository.saveReader(reader);
    }

    @DeleteMapping("/deleteReader/{id}")
    public  void deleteReader(@PathVariable("id") int id)
    {
        readerRepository.deleteReader(id);
    }

    @PutMapping("/updateReader")
    public void updateReader(@RequestBody Reader reader )
    {
        readerRepository.updateReader(reader);
    }
}
