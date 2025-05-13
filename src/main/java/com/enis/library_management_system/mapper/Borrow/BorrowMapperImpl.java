package com.enis.library_management_system.mapper.Borrow;

import com.enis.library_management_system.dto.BorrowingRecord.BorrowingRecordRequestDto;
import com.enis.library_management_system.dto.BorrowingRecord.BorrowingRecordResponseDto;
import com.enis.library_management_system.dto.BorrowingRecord.OverdueResponse;
import com.enis.library_management_system.dto.BorrowingRecord.ReturnRequest;
import com.enis.library_management_system.dto.BorrowingRecord.ReturnResponse;
import com.enis.library_management_system.entity.Book;
import com.enis.library_management_system.entity.BorrowingRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowMapperImpl implements BorrowMapper {

    @Override
    public BorrowingRecordResponseDto toResponseDto(BorrowingRecord record) {
        if (record == null) {
            return null;
        }
        
        BorrowingRecordResponseDto dto = new BorrowingRecordResponseDto();
        dto.setId(record.getId());
        dto.setBookId(record.getBook().getId());
        dto.setBookTitle(record.getBook().getTitle());
        dto.setUserId(record.getUser().getId());
        dto.setUserName(record.getUser().getName());
        dto.setBorrowDate(record.getBorrowDate());
        dto.setDueDate(record.getDueDate());
        dto.setReturnDate(record.getReturnDate());
        
        return dto;
    }

    @Override
    public List<BorrowingRecordResponseDto> toResponseDtoList(List<BorrowingRecord> records) {
        if (records == null) {
            return null;
        }
        
        return records.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReturnResponse toReturnResponse(BorrowingRecord record) {
        if (record == null) {
            return null;
        }
        
        ReturnResponse response = new ReturnResponse();
        response.setBorrowingRecordId(record.getId());
        response.setBookId(record.getBook().getId());
        response.setBookTitle(record.getBook().getTitle());
        response.setBorrowDate(record.getBorrowDate());
        response.setDueDate(record.getDueDate());
        response.setReturnDate(record.getReturnDate());
        
        // Calculate if overdue
        boolean isOverdue = record.getReturnDate() != null && 
                record.getReturnDate().isAfter(record.getDueDate());
        response.setOverdue(isOverdue);
        
        // Calculate overdue days
        int overdueDay = 0;
        if (isOverdue) {
            overdueDay = (int) ChronoUnit.DAYS.between(record.getDueDate(), record.getReturnDate());
        }
        response.setOverdueDay(overdueDay);
        
        return response;
    }

    @Override
    public OverdueResponse toOverdueResponse(BorrowingRecord record) {
        if (record == null) {
            return null;
        }
        
        OverdueResponse response = new OverdueResponse();
        response.setBorrowingRecordId(record.getId());
        response.setBookId(record.getBook().getId());
        response.setBookTitle(record.getBook().getTitle());
        response.setUserId(record.getUser().getId());
        response.setUserName(record.getUser().getName());
        response.setUserEmail(record.getUser().getEmail());
        response.setBorrowDate(record.getBorrowDate());
        response.setDueDate(record.getDueDate());
        
        // Calculate days overdue
        LocalDate today = LocalDate.now();
        int daysOverdue = (int) ChronoUnit.DAYS.between(record.getDueDate(), today);
        response.setDaysOverdue(daysOverdue);
        
        return response;
    }

    @Override
    public BorrowingRecord toBorrowingRecord(BorrowingRecordRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        BorrowingRecord record = new BorrowingRecord();
        
        // Set book ID - service will fetch the actual book
        Book book = new Book();
        book.setId(requestDto.getBookId());
        record.setBook(book);
        
        // Set dates
        record.setBorrowDate(requestDto.getBorrowDate());
        record.setDueDate(requestDto.getDueDate());
        
        return record;
    }

    @Override
    public BorrowingRecord toReturnRecord(ReturnRequest returnRequest) {
        if (returnRequest == null) {
            return null;
        }
        
        BorrowingRecord record = new BorrowingRecord();
        record.setId(returnRequest.getBorrowingRecordId());
        record.setReturnDate(returnRequest.getReturnDate());
        
        return record;
    }
}