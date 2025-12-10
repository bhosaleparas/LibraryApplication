package com.example.LibraryApplication.dto;




import com.example.LibraryApplication.entity.BorrowRecord;
import java.time.LocalDate;


public class BorrowRecordDTO {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BorrowRecord.Status status;
    private Long userId;
    private Long bookId;
    private String userName;
    private String bookTitle;

    // Constructors
    public BorrowRecordDTO() {}

    public BorrowRecordDTO(Long id, LocalDate borrowDate, LocalDate returnDate,
                           BorrowRecord.Status status, Long userId, Long bookId,
                           String userName, String bookTitle) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.userId = userId;
        this.bookId = bookId;
        this.userName = userName;
        this.bookTitle = bookTitle;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowRecord.Status getStatus() {
        return status;
    }

    public void setStatus(BorrowRecord.Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}