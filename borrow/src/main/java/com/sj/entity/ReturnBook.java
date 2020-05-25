package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBook {
    private Integer id;
    private Book book;
    private Reader reader;
    private String returnTime;
    private BookAdmin bookAdmin;
}
