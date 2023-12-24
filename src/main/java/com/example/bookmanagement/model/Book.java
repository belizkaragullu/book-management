package com.example.bookmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name= "book")
public class Book {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "released_day")
    private Date releasedDay;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;
}
