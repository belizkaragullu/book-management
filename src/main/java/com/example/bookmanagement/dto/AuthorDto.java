package com.example.bookmanagement.dto;

import com.example.bookmanagement.model.Book;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class AuthorDto {

    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private List<Book> bookList;
}
