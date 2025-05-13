package com.enis.library_management_system.mapper.Book;

import com.enis.library_management_system.dto.Book.BookRequestDto;
import com.enis.library_management_system.dto.Book.BookResponseDto;
import com.enis.library_management_system.entity.Book;

public interface BookMapper {
    BookResponseDto mapToDto(Book book);
    Book mapToBook(BookRequestDto dto);
    Book updateBook(Book book, BookRequestDto dto);
}
