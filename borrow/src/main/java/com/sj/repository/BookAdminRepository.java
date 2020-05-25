package com.sj.repository;

import com.sj.entity.BookAdmin;

public interface BookAdminRepository {
    public BookAdmin findById(int id);
}
