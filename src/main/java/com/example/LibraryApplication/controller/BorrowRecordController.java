package com.example.LibraryApplication.controller;




import com.example.LibraryApplication.dto.BorrowRecordDTO;
import com.example.LibraryApplication.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;



    @PostMapping("/borrow")
    public ResponseEntity<BorrowRecordDTO> borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        BorrowRecordDTO borrowRecord = borrowRecordService.borrowBook(userId, bookId);
        return new ResponseEntity<>(borrowRecord, HttpStatus.CREATED);
    }



    @PutMapping("/return/{id}")
    public ResponseEntity<BorrowRecordDTO> returnBook(@PathVariable Long id) {
        BorrowRecordDTO returnedRecord = borrowRecordService.returnBook(id);
        return ResponseEntity.ok(returnedRecord);
    }




    @GetMapping
    public ResponseEntity<List<BorrowRecordDTO>> getAllBorrowRecords() {
        List<BorrowRecordDTO> records = borrowRecordService.getAllBorrowRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsByUser(@PathVariable Long userId) {
        List<BorrowRecordDTO> records = borrowRecordService.getBorrowRecordsByUser(userId);
        return ResponseEntity.ok(records);
    }


    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsByBook(@PathVariable Long bookId) {
        List<BorrowRecordDTO> records = borrowRecordService.getBorrowRecordsByBook(bookId);
        return ResponseEntity.ok(records);
    }



    @GetMapping("/status/{status}")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsByStatus(@PathVariable String status) {
        List<BorrowRecordDTO> records = borrowRecordService.getBorrowRecordsByStatus(status);
        return ResponseEntity.ok(records);
    }
}