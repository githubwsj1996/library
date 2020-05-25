package com.sj.repository;

import com.sj.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
}
