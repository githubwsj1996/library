package com.sj.feign;

import com.sj.entity.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "borrow")
public interface BorrowFeign {

    @PostMapping("/borrow/saveBorrow")
    public void saveBorrow(@RequestBody Borrow borrow);

    @GetMapping("/borrow/findByReaderId/{index}/{limit}/{readerid}")
    public BorrowVO findByReaderId(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("readerid")int readerid);

    @GetMapping("/borrow/findAllByState/{index}/{limit}/{state}")
    public BorrowVO findAllByState(@PathVariable("index")int index,@PathVariable("limit") int limit,@PathVariable("state")int state);

    @PutMapping("/borrow/updateBorrow/{id}/{bookAdminId}/{state}")
    public void updateBorrow(@PathVariable("id") int id,@PathVariable("bookAdminId")int bookAdminId,@PathVariable("state") int state);

    @GetMapping("/borrow/findBackBook/{index}/{limit}")
    public BorrowVO findBackBook(@PathVariable("index")int index,@PathVariable("limit")int limit);

    @PutMapping("/borrow/updateBorrowBack/{id}/{bookAdminId}/{returnTime}/{state}")
    public void updateBorrowBack(@PathVariable("id")int id,@PathVariable("bookAdminId")int bookAdminId,@PathVariable("returnTime")String returnTime,@PathVariable("state") int state);

    @PostMapping("/borrow/saveReturn")
    public void saveReturn(@RequestBody ReturnBook returnBook);

    @GetMapping("/borrow/findReturnBook/{adminid}/{index}/{limit}")
    public ReturnBookVO findReturnBook(@PathVariable("adminid")int id,@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/borrow/getPieData")
    public List<PieData> getPieData();

    @GetMapping("/borrow/getBarData")
    public BarData getBarData();

    @GetMapping("/borrow/getHSSFWorkBook/{adminid}")
    public List<Borrow> getHSSFWorkBook(@PathVariable("adminid")int adminid);

    @GetMapping("/borrow/getReaderBorrow/{readerid}")
    public List<Borrow> getReaderBorrow(@PathVariable("readerid")int readerid);
}
