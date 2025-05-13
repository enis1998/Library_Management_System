package com.enis.library_management_system.mapper.Borrow;

import com.enis.library_management_system.dto.BorrowingRecord.*;
import com.enis.library_management_system.entity.BorrowingRecord;

import java.util.List;

public interface BorrowMapper {
    // Convert entity to DTO
    BorrowingRecordResponseDto toResponseDto(BorrowingRecord borrowingRecord);
    List<BorrowingRecordResponseDto> toResponseDtoList(List<BorrowingRecord> borrowingRecords);
    ReturnResponse toReturnResponse(BorrowingRecord borrowingRecord);
    OverdueResponse toOverdueResponse(BorrowingRecord record);

    // Convert DTO to entity
    BorrowingRecord toBorrowingRecord(BorrowingRecordRequestDto requestDto);
    BorrowingRecord toReturnRecord(ReturnRequest returnRequest);

}