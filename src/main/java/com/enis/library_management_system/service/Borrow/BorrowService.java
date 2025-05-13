package com.enis.library_management_system.service.Borrow;

import com.enis.library_management_system.entity.BorrowingRecord;

import java.util.List;

public interface BorrowService {
    BorrowingRecord createBorrowRecord(BorrowingRecord record, String username);
    BorrowingRecord returnBorrowingRecord(BorrowingRecord record, String username);
    List<BorrowingRecord> getBorrowingRecordsForUser(String username);
    List<BorrowingRecord> getAllBorrowingRecords();
    List<BorrowingRecord> getOverdueBorrowingRecords();
}