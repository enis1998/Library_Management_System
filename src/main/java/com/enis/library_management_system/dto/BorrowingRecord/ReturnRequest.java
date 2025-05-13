package com.enis.library_management_system.dto.BorrowingRecord;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class ReturnRequest {
    @NotNull(message = "Borrowing record ID must not be null")
    private UUID borrowingRecordId;

    @NotNull(message = "Return date must not be null")
    private LocalDate returnDate;

    public ReturnRequest() {
    }

    public UUID getBorrowingRecordId() {
        return borrowingRecordId;
    }

    public void setBorrowingRecordId(UUID borrowingRecordId) {
        this.borrowingRecordId = borrowingRecordId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

