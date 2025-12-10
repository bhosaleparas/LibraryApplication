package com.example.LibraryApplication.repository;




import com.example.LibraryApplication.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserId(Long userId);
    List<BorrowRecord> findByBookId(Long bookId);
    List<BorrowRecord> findByStatus(BorrowRecord.Status status);
    boolean existsByBookIdAndStatus(Long bookId, BorrowRecord.Status status);
}