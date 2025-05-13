package com.enis.library_management_system.service.Book;

import com.enis.library_management_system.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {

    Book getBookById(UUID id);

    Book saveBook(Book book);

    Page<Book> searchBooks(String title, String author, String isbn, String genre, Pageable pageable);

    void deleteBook(UUID id);
}