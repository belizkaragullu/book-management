package com.example.bookmanagement.dto;

import com.example.bookmanagement.model.Author;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookDto {

    private Long id;
    private String name;
    private Date releasedDay;
    private Author author;
}
