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

    public BorrowingRecordRequestDto() {
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
