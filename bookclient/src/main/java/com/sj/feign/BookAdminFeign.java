package com.sj.feign;

import com.sj.entity.Reader;
import com.sj.entity.ReaderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "reader")
public interface BookAdminFeign {

    @GetMapping("/reader/findAll/{index}/{limit}")
    public ReaderVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @PostMapping("/reader/saveReader")
    public void saveReader(@RequestBody Reader reader);

    @DeleteMapping("/reader/deleteReader/{id}")
    public void deleteReader(@PathVariable("id")int id);

    @GetMapping("/reader/findById/{id}")
    public Reader findById (@PathVariable("id") int id);

    @PutMapping("/reader/updateReader")
    public Reader updateReader(@RequestBody Reader reader);

}
