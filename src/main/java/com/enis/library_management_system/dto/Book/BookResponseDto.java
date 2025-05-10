package com.enis.library_management_system.dto.Book;

import java.time.LocalDate;
import java.util.UUID;

public class BookResponseDto {
    private UUID id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate publicationDate;

    private String genre;

    private Boolean available;

}
