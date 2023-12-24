package com.example.bookmanagement.service.serviceImpl;

import com.example.bookmanagement.dto.BookDto;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.AuthorRepository;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private BookDto mapToBookDto(Book book){
        BookDto bookDto=BookDto.builder()
                .name(book.getName())
                .releasedDay(book.getReleasedDay())
                .id(book.getId())
                .author(book.getAuthor())
                .build();

        return bookDto;
    }
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book) -> mapToBookDto(book)).collect(Collectors.toList());    }

    @Override
    public List<BookDto> getAllBooksByAuthorId(Long authorId) {
        if(Boolean.FALSE.equals(authorRepository.findById(authorId))){
            throw new ResourceNotFoundException("Author does not exists.");
        }

        List<Book> books = bookRepository.findByAuthorId(authorId);
        return books.stream().map((book) -> mapToBookDto(book)).collect(Collectors.toList());

    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("This book does not exist"));
        return mapToBookDto(book);
    }

    @Override
    public BookDto createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return mapToBookDto(savedBook);

    }

    @Override
    public BookDto updateBook(Long id, Book book) {
        Book updatedBook = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("You can't update a book that doesn't exist"));

        updatedBook.setName(book.getName());
        updatedBook.setReleasedDay(book.getReleasedDay());
        updatedBook.setAuthor(book.getAuthor());

         bookRepository.save(updatedBook);
         return mapToBookDto(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        if(Boolean.FALSE.equals(bookRepository.findById(bookId))){
            throw new ResourceNotFoundException("You can't delete a book that doesn't exist");
        }

        bookRepository.deleteById(bookId);
    }
}
