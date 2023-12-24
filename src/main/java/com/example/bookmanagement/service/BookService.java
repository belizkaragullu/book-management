package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.BookDto;
import com.example.bookmanagement.model.Book;


import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();
    List<BookDto> getAllBooksByAuthorId(Long id);
    BookDto getBookById(Long id);
    BookDto createBook(Book book);
    BookDto updateBook(Long id, Book book);
    void deleteBook(Long id);

}
