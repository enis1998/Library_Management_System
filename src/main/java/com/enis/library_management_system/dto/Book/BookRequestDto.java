package com.enis.library_management_system.dto.Book;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookRequestDto {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    private LocalDate publicationDate;

    private String genre;

    @NotNull
    private Boolean available;
}

