package com.sj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private Integer id;
    private Book book;
    private Reader reader;
    private String borrowTime;
    private String returnTime;
    private BookAdmin admin;
    private Integer state;
}
