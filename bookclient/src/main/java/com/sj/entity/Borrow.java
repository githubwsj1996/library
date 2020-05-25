package com.sj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Borrow {
    private Integer Id;
    private Book book;
    private Reader reader;
    private String  borrowTime;
    private String returnTime;
    private BookAdmin admin;
    private Integer state;


    public Borrow(Book book, Reader reader, String borrowTime, String returnTime) {
        this.book = book;
        this.reader = reader;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }
}
