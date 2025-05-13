package com.enis.library_management_system.controller.Borrowing.contract;

import com.enis.library_management_system.dto.BorrowingRecord.BorrowingRecordRequestDto;
import com.enis.library_management_system.dto.BorrowingRecord.BorrowingRecordResponseDto;
import com.enis.library_management_system.dto.BorrowingRecord.OverdueResponse;
import com.enis.library_management_system.dto.BorrowingRecord.ReturnRequest;
import com.enis.library_management_system.dto.BorrowingRecord.ReturnResponse;

import java.security.Principal;
import java.util.List;

public interface BorrowContract {
    BorrowingRecordResponseDto borrowBook(Principal user, BorrowingRecordRequestDto request);
    ReturnResponse returnBook(String username, ReturnRequest request);
    List<BorrowingRecordResponseDto> getHistoryForUser(String username);
    List<BorrowingRecordResponseDto> getAllHistory();
    List<OverdueResponse> getOverdueBooks();
}