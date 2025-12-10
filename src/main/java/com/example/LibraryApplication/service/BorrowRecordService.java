package com.example.LibraryApplication.service;



import com.example.LibraryApplication.dto.BorrowRecordDTO;
import com.example.LibraryApplication.entity.Book;
import com.example.LibraryApplication.entity.BorrowRecord;
import com.example.LibraryApplication.entity.User;
import com.example.LibraryApplication.repository.BookRepository;
import com.example.LibraryApplication.repository.BorrowRecordRepository;
import com.example.LibraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;




@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public BorrowRecordDTO borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        // Check if book is already borrowed and not returned
        if (borrowRecordRepository.existsByBookIdAndStatus(bookId, BorrowRecord.Status.BORROWED)) {
            throw new RuntimeException("Book is already borrowed");
        }

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setStatus(BorrowRecord.Status.BORROWED);

        // Update book availability
        book.setAvailable(false);
        bookRepository.save(book);

        BorrowRecord savedRecord = borrowRecordRepository.save(borrowRecord);
        return convertToDTO(savedRecord);
    }



    public BorrowRecordDTO returnBook(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (borrowRecord.getStatus() == BorrowRecord.Status.RETURNED) {
            throw new RuntimeException("Book already returned");
        }


        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecord.setStatus(BorrowRecord.Status.RETURNED);

        // Update book availability
        Book book = borrowRecord.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        BorrowRecord updatedRecord = borrowRecordRepository.save(borrowRecord);
        return convertToDTO(updatedRecord);
    }



    public List<BorrowRecordDTO> getAllBorrowRecords() {
        return borrowRecordRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BorrowRecordDTO> getBorrowRecordsByUser(Long userId) {
        return borrowRecordRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BorrowRecordDTO> getBorrowRecordsByBook(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public List<BorrowRecordDTO> getBorrowRecordsByStatus(String status) {
        BorrowRecord.Status recordStatus = BorrowRecord.Status.valueOf(status.toUpperCase());
        return borrowRecordRepository.findByStatus(recordStatus)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    private BorrowRecordDTO convertToDTO(BorrowRecord borrowRecord) {
        return new BorrowRecordDTO(
                borrowRecord.getId(),
                borrowRecord.getBorrowDate(),
                borrowRecord.getReturnDate(),
                borrowRecord.getStatus(),
                borrowRecord.getUser().getId(),
                borrowRecord.getBook().getId(),
                borrowRecord.getUser().getName(),
                borrowRecord.getBook().getTitle()
        );
    }
}
