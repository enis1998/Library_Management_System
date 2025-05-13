package com.enis.library_management_system.controller.Borrowing;

import com.enis.library_management_system.controller.Borrowing.contract.BorrowContract;
import com.enis.library_management_system.dto.BorrowingRecord.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@Validated
public class BorrowController {
    @Autowired
    private BorrowContract borrowContract;

    public BorrowController(BorrowContract borrowContract) {
        this.borrowContract = borrowContract;
    }

    @PostMapping
    public ResponseEntity<BorrowingRecordResponseDto> borrowBook(@Valid @RequestBody BorrowingRecordRequestDto request, Principal principal) {
        BorrowingRecordResponseDto response = borrowContract.borrowBook(principal, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> returnBook(@Valid @RequestBody ReturnRequest request, Principal principal) {
        ReturnResponse response = borrowContract.returnBook(principal.getName(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<BorrowingRecordResponseDto>> getUserHistory(Principal principal) {
        List<BorrowingRecordResponseDto> history = borrowContract.getHistoryForUser(principal.getName());
        return ResponseEntity.ok(history);
    }

    @GetMapping("/history/all")
    public ResponseEntity<List<BorrowingRecordResponseDto>> getAllHistory() {
        List<BorrowingRecordResponseDto> history = borrowContract.getAllHistory();
        return ResponseEntity.ok(history);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<OverdueResponse>> getOverdue() {
        List<OverdueResponse> overdue = borrowContract.getOverdueBooks();
        return ResponseEntity.ok(overdue);
    }
}

