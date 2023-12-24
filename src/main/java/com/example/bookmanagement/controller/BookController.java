package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.BookDto;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.service.serviceImpl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> getAllBooksByAuthorId(@PathVariable Long authorId) {
        List<BookDto> books = bookService.getAllBooksByAuthorId(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody Book book) {
        BookDto createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody Book book) {
        BookDto updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
