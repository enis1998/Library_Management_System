package com.enis.library_management_system.controller.Borrowing.contract.Impl;

import com.enis.library_management_system.controller.Borrowing.contract.BorrowContract;
import com.enis.library_management_system.dto.BorrowingRecord.BorrowingRecordRequestDto;
import com.enis.library_management_system.dto.BorrowingRecord.BorrowingRecordResponseDto;
import com.enis.library_management_system.dto.BorrowingRecord.OverdueResponse;
import com.enis.library_management_system.dto.BorrowingRecord.ReturnRequest;
import com.enis.library_management_system.dto.BorrowingRecord.ReturnResponse;
import com.enis.library_management_system.entity.BorrowingRecord;
import com.enis.library_management_system.mapper.Borrow.BorrowMapper;
import com.enis.library_management_system.service.Borrow.BorrowService;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowContractImpl implements BorrowContract {
    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    public BorrowContractImpl(BorrowService borrowService, BorrowMapper borrowMapper) {
        this.borrowService = borrowService;
        this.borrowMapper = borrowMapper;
    }

    @Override
    public BorrowingRecordResponseDto borrowBook(Principal principal, BorrowingRecordRequestDto request) {
        BorrowingRecord borrowingRecord = borrowMapper.toBorrowingRecord(request);
        BorrowingRecord savedRecord = borrowService.createBorrowRecord(borrowingRecord, principal.getName());
        return borrowMapper.toResponseDto(savedRecord);
    }

    @Override
    public ReturnResponse returnBook(String username, ReturnRequest request) {
        BorrowingRecord recordToReturn = borrowMapper.toReturnRecord(request);
        BorrowingRecord returnedRecord = borrowService.returnBorrowingRecord(recordToReturn, username);
        return borrowMapper.toReturnResponse(returnedRecord);
    }

    @Override
    public List<BorrowingRecordResponseDto> getHistoryForUser(String username) {
        List<BorrowingRecord> records = borrowService.getBorrowingRecordsForUser(username);
        return borrowMapper.toResponseDtoList(records);
    }

    @Override
    public List<BorrowingRecordResponseDto> getAllHistory() {
        List<BorrowingRecord> records = borrowService.getAllBorrowingRecords();
        return borrowMapper.toResponseDtoList(records);
    }

    @Override
    public List<OverdueResponse> getOverdueBooks() {
        List<BorrowingRecord> overdueRecords = borrowService.getOverdueBorrowingRecords();
        return overdueRecords.stream()
                .map(borrowMapper::toOverdueResponse)
                .collect(Collectors.toList());
    }
}