package com.sj.controller;

import com.sj.entity.*;
import com.sj.repository.BorrowRepository;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowHandler {

    @Autowired
    private BorrowRepository borrowRepository;


    @PostMapping("/saveBorrow")
    public void saveBorrow(@RequestBody Borrow borrow)
    {
        borrowRepository.saveBorrow(borrow);
    }

    @GetMapping("/findByReaderId/{index}/{limit}/{readerid}")
    public BorrowVO findByReaderId(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("readerid") int readerid)
    {
        return new BorrowVO(0,"",borrowRepository.countReaderId(readerid),borrowRepository.findByReaderId(index,limit,readerid));
    }

    @GetMapping("/findAllByState/{index}/{limit}/{state}")
    public BorrowVO findAllByState(@PathVariable("index") int index,@PathVariable("limit")int limit,@PathVariable("state")int state)
    {
        return new BorrowVO(0,"",borrowRepository.countState(),borrowRepository.findAllByState(index,limit,state));
    }

    @PutMapping("/updateBorrow/{id}/{bookAdminId}/{state}")
    public void updateBorrow(@PathVariable("id")int id,@PathVariable("bookAdminId")int bookAdminId,@PathVariable("state") int state){
        borrowRepository.updateBorrow(id,bookAdminId,state);
    }

    @GetMapping("findBackBook/{index}/{limit}")
    public BorrowVO findBackBook(@PathVariable("index")int index,@PathVariable("limit")int limit){
        return  new BorrowVO(0,"",borrowRepository.countBack(),borrowRepository.findBackBook(index,limit));
    }

    @PutMapping("updateBorrowBack/{id}/{bookAdminId}/{returnTime}/{state}")
    public void updateBorrowBack(@PathVariable("id")int id,@PathVariable("bookAdminId")int bookAdminId,@PathVariable("returnTime")String returnTime,@PathVariable("state") int state){
        borrowRepository.updateBorrowBack(id,bookAdminId,returnTime,state);
    }

    @PostMapping("/saveReturn")
    public void saveReturn(@RequestBody ReturnBook returnBook)
    {
        borrowRepository.saveReturn(returnBook);
    }

    @GetMapping("/findReturnBook/{adminid}/{index}/{limit}")
    public ReturnBookVO findReturnBook(@PathVariable("adminid") int adminid,@PathVariable("index") int index,@PathVariable("limit")int limit)
    {
        return new ReturnBookVO(0,"",borrowRepository.countAdmin(adminid),borrowRepository.findReturnByAdmin(adminid,index,limit));
    }

    @GetMapping("/getPieData")
    public List<PieData> getPieData()
    {
        List<PieData> pieDataList=new ArrayList<>();
        List<Borrow> borrowList=borrowRepository.findBookIdCount();
        int count[]=borrowRepository.count();
        PieData pieData=null;
        for(int i=0;i<borrowList.size();i++)
        {
            String name=borrowList.get(i).getBook().getName();
            Integer value=count[i];
            pieData=new PieData(value,name);
            pieDataList.add(pieData);
        }
        return  pieDataList;
    }

    @GetMapping("/getBarData")
    public BarData getBarData()
    {
        BarData barData=new BarData();
        List<String> names=new ArrayList<>();
        List<Integer> counts=new ArrayList<>();
        List<Borrow> borrowList=borrowRepository.findBookIdCount();
        int[] count=borrowRepository.count();
        for(int i=0;i<borrowList.size();i++)
        {
            names.add(borrowList.get(i).getBook().getName());
            counts.add(count[i]);
        }
        barData.setName(names);
        barData.setCount(counts);
        return  barData;
    }

    @GetMapping("/getHSSFWorkBook/{adminid}")
    public List<Borrow> getHSSFWorkBook(@PathVariable("adminid") int adminid){
        return  borrowRepository.findBorrowByAdmin(adminid);
    }

    @GetMapping("/getReaderBorrow/{readerid}")
    public List<Borrow> getReaderBorrow(@PathVariable("readerid") int readerid)
    {
        return borrowRepository.findBorrowByReader(readerid);
    }
}
