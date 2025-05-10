package com.enis.library_management_system.dto.BorrowingRecord;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowingRecordResponseDto {
    private UUID id;

    private UUID bookId;

    private UUID userId;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

}
