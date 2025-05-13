package com.enis.library_management_system.mapper.Book;

import com.enis.library_management_system.dto.Book.BookRequestDto;
import com.enis.library_management_system.dto.Book.BookResponseDto;
import com.enis.library_management_system.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {

    public BookResponseDto mapToDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setGenre(book.getGenre());
        dto.setAvailable(book.isAvailable());
        return dto;
    }

    public Book mapToBook(BookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationDate(dto.getPublicationDate());
        book.setGenre(dto.getGenre());
        book.setAvailable(dto.getAvailable());
        return book;
    }

    public Book updateBook(Book book, BookRequestDto dto) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationDate(dto.getPublicationDate());
        book.setGenre(dto.getGenre());
        book.setAvailable(dto.getAvailable());
        return book;
    }

}
