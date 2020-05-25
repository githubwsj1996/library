package com.sj.repository;

import com.sj.entity.BookCase;

import java.util.List;

public interface BookCaseRepository {
    public List<BookCase> findAll();
    public BookCase findById(int id);
}
