package com.enis.library_management_system.dto.BorrowingRecord;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowingRecordRequestDto {
    @NotNull(message = "Book ID must not be null")
    private UUID bookId;

    @NotNull(message = "User ID must not be null")
    private UUID userId;

    @NotNull(message = "Borrow date must not be null")
    private LocalDate borrowDate;

    @NotNull(message = "Due date must not be null")
    private LocalDate dueDate;

    private LocalDate returnDate;

}
