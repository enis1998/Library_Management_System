package com.enis.library_management_system.controller.Book.contract;

import com.enis.library_management_system.dto.Book.BookRequestDto;
import com.enis.library_management_system.dto.Book.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookContract {
    BookResponseDto getBookById(UUID id);
    BookResponseDto addBook(BookRequestDto bookRequestDto);
    BookResponseDto updateBook(UUID id, BookRequestDto request);
    Page<BookResponseDto> searchBooks(String title, String author, String isbn, String genre, Pageable pageable);
}
