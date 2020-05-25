package com.sj.repository;

import com.sj.entity.Reader;

import java.util.List;

public interface ReaderRepository {
    public List<Reader> findAll(int index,int limit);
    public int countReader();
    public Reader findById(int id);
    public void saveReader(Reader reader);
    public void deleteReader(int id);
    public void updateReader(Reader reader);
}
