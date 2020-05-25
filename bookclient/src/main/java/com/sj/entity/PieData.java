package com.sj.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PieData {
    private Integer value;
    private String name;


    @Override
    public String toString() {
        return "PieData{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
