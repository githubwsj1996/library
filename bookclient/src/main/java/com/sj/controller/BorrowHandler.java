package com.sj.controller;

import com.sj.entity.*;
import com.sj.feign.BookMenuFeign;
import com.sj.feign.BorrowFeign;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowHandler {

    @Autowired
    private BorrowFeign borrowFeign;

    @Autowired
    private BookMenuFeign bookMenuFeign;

    @GetMapping("/saveBorrow/{id}")
    public String saveBorrow(@PathVariable("id") int id, HttpSession session)
    {
        Reader reader=(Reader) session.getAttribute("reader");
        Book book=new Book();
        book.setId(id);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime=sdf.format(date);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+14);
        String returnTime=sdf.format(calendar.getTime());
        Borrow borrow=new Borrow(book,reader,borrowTime,returnTime);
        borrowFeign.saveBorrow(borrow);
        bookMenuFeign.updateAbled(id);
        return "redirect:/bookMenu/redirect/reader_borrow";
    }

    @GetMapping("/findByReaderId")
    @ResponseBody
    public BorrowVO findByReaderId(@RequestParam int page,@RequestParam int limit,HttpSession session)
    {
        Reader reader=(Reader) session.getAttribute("reader");
        int index=(page-1)*limit;
        return borrowFeign.findByReaderId(index,limit,reader.getId());
    }

    @GetMapping("/findAllByState")
    @ResponseBody
    public BorrowVO findAllByState(@RequestParam int page,@RequestParam int limit)
    {
        int index=(page-1)*limit;
        return  borrowFeign.findAllByState(index,limit,0);
    }

    @GetMapping("/agreeBorrowed/{id}")
    public String agreeBorrowed(@PathVariable("id") int id,HttpSession session)
    {
        BookAdmin bookAdmin=(BookAdmin) session.getAttribute("bookAdmin");
        int bookAdminId=bookAdmin.getId();
        int state=1;
        borrowFeign.updateBorrow(id,bookAdminId,state);
        return "redirect:/bookMenu/redirect/borrow_manage";
    }

    @GetMapping("/disagreeBorrowed/{id}/{bookId}")
    public String disagreeBorrowed(@PathVariable("id") int id,@PathVariable("bookId") int bookId,HttpSession session)
    {
        BookAdmin bookAdmin=(BookAdmin) session.getAttribute("bookAdmin");
        int bookAdminId=bookAdmin.getId();
        int state=2;
        borrowFeign.updateBorrow(id,bookAdminId,state);
        bookMenuFeign.updateAbledById(bookId);
        return "redirect:/bookMenu/redirect/borrow_manage";
    }

    @GetMapping("/findBackBook")
    @ResponseBody
    public BorrowVO findBackBook(@RequestParam int page,@RequestParam int limit)
    {
        int index=(page-1)*limit;
        return  borrowFeign.findBackBook(index,limit);
    }

    @GetMapping("/updateBorrowBack/{id}/{bookId}/{readId}")
    public String updateBorrowBack(@PathVariable("id") int id,@PathVariable("bookId") int bookId,@PathVariable("readId")int readId,HttpSession session)
    {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(("yyyy-MM-dd"));
        String returnTime=sdf.format(date);
        BookAdmin bookAdmin=(BookAdmin)session.getAttribute("bookAdmin");

        borrowFeign.updateBorrowBack(id,bookAdmin.getId(),returnTime,3);
        bookMenuFeign.updateAbledById(bookId);

        Book book=new Book();
        book.setId(bookId);
        Reader reader=new Reader();
        reader.setId(readId);
        bookAdmin.setId(bookAdmin.getId());
        ReturnBook returnBook=new ReturnBook(book,reader,returnTime,bookAdmin);
        borrowFeign.saveReturn(returnBook);

        return "redirect:/bookMenu/redirect/return_manage";
    }

    @GetMapping("/findReturn")
    @ResponseBody
    public ReturnBookVO findReturnBook(@RequestParam int page,@RequestParam int limit,HttpSession session)
    {
        int index=(page-1)*limit;
        BookAdmin bookAdmin=(BookAdmin) session.getAttribute("bookAdmin");
        int adminid=bookAdmin.getId();
        return  borrowFeign.findReturnBook(adminid,index,limit);
    }

    @GetMapping("/getPieData")
    @ResponseBody
    public List<PieData> getPieData()
    {
        return borrowFeign.getPieData();
    }

    @GetMapping("/getBarData")
    @ResponseBody
    public BarData getBarData()
    {
        return borrowFeign.getBarData();
    }

    @GetMapping("/exportBorrow")
    public void exportBorrow(HttpServletResponse response,HttpSession session) throws Exception
    {
        BookAdmin bookAdmin=(BookAdmin) session.getAttribute("bookAdmin");
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        Sheet sheet=hssfWorkbook.createSheet("借阅数据");
        List<Borrow> list=borrowFeign.getHSSFWorkBook(bookAdmin.getId());
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("借阅编号");
        cell=row.createCell(1);
        cell.setCellValue("图书名称");
        cell=row.createCell(2);
        cell.setCellValue("读者");
        cell=row.createCell(3);
        cell.setCellValue("借阅时间");
        cell=row.createCell(4);
        cell.setCellValue("还书时间");
        cell=row.createCell(5);
        cell.setCellValue("受理人");
        for(int i=0;i<list.size();i++) {
            Borrow borrow = list.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(borrow.getId());
            cell = row.createCell(1);
            cell.setCellValue(borrow.getBook().getName());
            cell = row.createCell(2);
            cell.setCellValue(borrow.getReader().getName());
            cell = row.createCell(3);
            cell.setCellValue(borrow.getBorrowTime());
            cell = row.createCell(4);
            cell.setCellValue(borrow.getReturnTime());
            cell = row.createCell(5);
            cell.setCellValue(borrow.getAdmin().getUsername());
        }
        response.setContentType("application/x-msdownload");
        String fileName=URLEncoder.encode("借阅信息.xls","utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        OutputStream outputStream=response.getOutputStream();
        hssfWorkbook.write(outputStream);
        outputStream.close();
    }


    @GetMapping("/exportReaderBorrow")
    public void exportReaderBorrow(HttpServletResponse response,HttpSession session) throws Exception
    {
        Reader reader=(Reader)session.getAttribute("reader");
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        Sheet sheet=hssfWorkbook.createSheet("借阅数据");
        List<Borrow> list=borrowFeign.getReaderBorrow(reader.getId());
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("借阅编号");
        cell=row.createCell(1);
        cell.setCellValue("图书名称");
        cell=row.createCell(2);
        cell.setCellValue("读者");
        cell=row.createCell(3);
        cell.setCellValue("借阅时间");
        cell=row.createCell(4);
        cell.setCellValue("还书时间");
        cell=row.createCell(5);
        cell.setCellValue("借阅状态");
        for(int i=0;i<list.size();i++) {
            Borrow borrow = list.get(i);
            String stateName="";
            Integer sate=borrow.getState();
            switch (sate){
                case 0:
                    stateName="未审核";
                    break;
                case 1:
                    stateName="审核通过";
                    break;
                case 2:
                    stateName="审核未通过";
                    break;
                case 3:
                    stateName="已归还";
                    break;
            }
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(borrow.getId());
            cell = row.createCell(1);
            cell.setCellValue(borrow.getBook().getName());
            cell = row.createCell(2);
            cell.setCellValue(borrow.getReader().getName());
            cell = row.createCell(3);
            cell.setCellValue(borrow.getBorrowTime());
            cell = row.createCell(4);
            cell.setCellValue(borrow.getReturnTime());
            cell = row.createCell(5);
            cell.setCellValue(stateName);
        }
        response.setContentType("application/x-msdownload");
        String fileName=URLEncoder.encode("借阅信息.xls","utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        OutputStream outputStream=response.getOutputStream();
        hssfWorkbook.write(outputStream);
        outputStream.close();
    }

}
