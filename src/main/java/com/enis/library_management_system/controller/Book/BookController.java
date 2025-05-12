package com.enis.library_management_system.controller.Book;

import com.enis.library_management_system.controller.Book.contract.BookContract;
import com.enis.library_management_system.dto.Book.BookRequestDto;
import com.enis.library_management_system.dto.Book.BookResponseDto;
import com.enis.library_management_system.service.Book.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    @Autowired
    private final BookContract bookContract;
    private final BookService bookService;

    public BookController(BookContract bookContract, BookService bookService) {
        this.bookContract = bookContract;
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable UUID id) {
        BookResponseDto bookResponseDto = bookContract.getBookById(id);
        return ResponseEntity.ok(bookResponseDto);
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto response = bookContract.addBook(bookRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> searchBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "genre", required = false) String genre,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<BookResponseDto> page = bookContract.searchBooks(title, author, isbn, genre, pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable UUID id,
            @Valid @RequestBody BookRequestDto request) {
        BookResponseDto response = bookContract.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
