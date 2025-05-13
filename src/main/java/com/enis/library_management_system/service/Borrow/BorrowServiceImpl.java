package com.enis.library_management_system.service.Borrow;

import com.enis.library_management_system.entity.BorrowingRecord;
import com.enis.library_management_system.enums.Role;
import com.enis.library_management_system.exception.AuthorizationException;
import com.enis.library_management_system.exception.BookNotAvailableException;
import com.enis.library_management_system.exception.ResourceNotFoundException;
import com.enis.library_management_system.repository.BookRepository;
import com.enis.library_management_system.repository.BorrowingRecordRepository;
import com.enis.library_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public BorrowServiceImpl(BorrowingRecordRepository borrowingRecordRepository,
                             BookRepository bookRepo,
                             UserRepository userRepo) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public BorrowingRecord createBorrowRecord(BorrowingRecord record, String username) {
        var user = userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        var book = bookRepo.findById(record.getBook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + record.getBook().getId()));
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Kitap ödünç alınmış.");
        }
        record.setUser(user);
        record.setBook(book);
        book.setAvailable(false);
        bookRepo.save(book);
        return borrowingRecordRepository.save(record);
    }

    @Override
    @Transactional
    public BorrowingRecord returnBorrowingRecord(BorrowingRecord stub, String username) {
        var user = userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        var record = borrowingRecordRepository.findById(stub.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Record not found: " + stub.getId()));
        if (!record.getUser().getId().equals(user.getId())
                && user.getRole() != Role.LIBRARIAN) {
            throw new AuthorizationException("Yetkisiz işlem");
        }

        record.setReturnDate(stub.getReturnDate());
        var book = record.getBook();
        book.setAvailable(true);
        bookRepo.save(book);
        return borrowingRecordRepository.save(record);
    }

    @Override
    public List<BorrowingRecord> getBorrowingRecordsForUser(String username) {
        var user = userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return borrowingRecordRepository.findByUser(user);
    }

    @Override
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    @Override
    public List<BorrowingRecord> getOverdueBorrowingRecords() {
        return borrowingRecordRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now());
    }
}