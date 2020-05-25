package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarData {
    private List<String> name;
    private List<Integer> count;

    @Override
    public String toString() {
        return "BarData{" +
                "name=" + name +
                ", count=" + count +
                '}';
    }
}
