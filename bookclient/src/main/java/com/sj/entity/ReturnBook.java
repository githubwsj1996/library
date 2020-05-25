package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReturnBook {
    private Integer id;
    private Book book;
    private Reader reader;
    private String returnTime;
    private BookAdmin bookAdmin;

    public ReturnBook(Book book, Reader reader, String returnTime, BookAdmin bookAdmin) {
        this.book = book;
        this.reader = reader;
        this.returnTime = returnTime;
        this.bookAdmin = bookAdmin;
    }
}
