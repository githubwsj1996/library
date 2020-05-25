package com.sj.repository;

import com.sj.entity.Book;

import java.util.List;

public interface BookMenuRepository {
    public List<Book> findAll(int index,int limit);
    public Book findById(int id);
    public void save(Book book);
    public void deleteById(int id);
    public void updateBook(Book book);
    public int count();
    public void updateAbled(int id);
    public void updateAbledById(int id);
}
