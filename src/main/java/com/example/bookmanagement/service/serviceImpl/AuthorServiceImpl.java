package com.example.bookmanagement.service.serviceImpl;

import com.example.bookmanagement.dto.AuthorDto;
import com.example.bookmanagement.model.Author;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.repository.AuthorRepository;
import com.example.bookmanagement.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private AuthorDto mapToAuthorDto(Author author){
        AuthorDto authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .bookList(author.getBookList())
                .dateOfBirth(author.getDateOfBirth())
                .build();
        return authorDto;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {

        List<Author> authors= authorRepository.findAll();
        return authors.stream().map((author) -> mapToAuthorDto(author)).collect(Collectors.toList());
    }


    public AuthorDto createAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return mapToAuthorDto(savedAuthor);
    }

    @Override
    public AuthorDto updateAuthor(Long id, Author author) {
         Author authorResult = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no author with this id"));

        authorResult.setName(author.getName());
        authorResult.setSurname(author.getSurname());
        authorResult.setDateOfBirth(author.getDateOfBirth());
        authorResult.setBookList(author.getBookList());


        authorRepository.save(authorResult);
        return mapToAuthorDto(authorResult);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author deletedAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no author with this id"));

        authorRepository.delete(deletedAuthor);

    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Optional<Author> author =  authorRepository.findById(id);

        if (author.isPresent()){
            Author authorResult = author.get();
            return mapToAuthorDto(authorResult);

        }
        else{
            throw new ResourceNotFoundException("There is no author with this id");
        }
    }

}
