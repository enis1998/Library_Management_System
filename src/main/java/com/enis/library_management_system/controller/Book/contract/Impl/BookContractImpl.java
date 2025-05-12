package com.enis.library_management_system.controller.Book.contract.Impl;

import com.enis.library_management_system.controller.Book.contract.BookContract;
import com.enis.library_management_system.dto.Book.BookRequestDto;
import com.enis.library_management_system.dto.Book.BookResponseDto;
import com.enis.library_management_system.entity.Book;
import com.enis.library_management_system.mapper.Book.BookMapper;
import com.enis.library_management_system.mapper.Book.BookMapperImpl;
import com.enis.library_management_system.service.Book.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookContractImpl implements BookContract {

    private final BookService bookService;
    private final BookMapperImpl mapper;
    private final BookMapper bookMapper;

    public BookContractImpl(BookService bookService, BookMapperImpl mapper, BookMapper bookMapper) {
        this.bookService = bookService;
        this.mapper = mapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto getBookById(UUID id) {
        Book book = bookService.getBookById(id);
        return mapper.mapToDto(book);
    }

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = mapper.mapToBook(bookRequestDto);
        Book savedBook = bookService.saveBook(book);
        return mapper.mapToDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(UUID id, BookRequestDto request) {
        Book book = bookService.getBookById(id);
        Book updatedBook = bookMapper.updateBook(book, request);
        bookService.saveBook(updatedBook);
        return mapper.mapToDto(updatedBook);
    }

    @Override
    public Page<BookResponseDto> searchBooks(String title, String author, String isbn, String genre, Pageable pageable) {
        Page<Book> booksPage = bookService.searchBooks(title, author, isbn, genre, pageable);
        return booksPage.map(mapper::mapToDto);
    }

}