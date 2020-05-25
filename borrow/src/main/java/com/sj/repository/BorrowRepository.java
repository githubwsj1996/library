package com.sj.repository;

import com.sj.entity.Borrow;
import com.sj.entity.ReturnBook;

import java.util.List;

public interface BorrowRepository {
    public void saveBorrow(Borrow borrow);
    public List<Borrow> findByReaderId(int index,int limit,int readerid);
    public int countReaderId(int readerid);

    public List<Borrow> findAllByState(int index,int limit,int state);
    public int countState();
    public void updateBorrow(int id,int bookAdminId,int state);

    public List<Borrow> findBackBook(int index,int limit);
    public int countBack();
    public void updateBorrowBack(int id,int bookAdminId,String returnTime,int state);

    public void saveReturn(ReturnBook returnBook);
    public List<ReturnBook> findReturnByAdmin(int id,int index,int limit);
    public int countAdmin(int adminid);

    //饼状，条状数据
    public List<Borrow> findBookIdCount();
    public int[] count();

    //导出borrow数据admin
    public List<Borrow> findBorrowByAdmin(int adminid);
    public List<Borrow> findBorrowByReader(int readerid);
}
