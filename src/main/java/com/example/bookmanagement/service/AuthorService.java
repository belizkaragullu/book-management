package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.AuthorDto;
import com.example.bookmanagement.model.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();

    AuthorDto createAuthor(Author author);

    AuthorDto updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);

    AuthorDto getAuthorById(Long id);


}
