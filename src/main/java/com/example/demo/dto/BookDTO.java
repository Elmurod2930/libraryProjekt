package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private Timestamp publishYear;
}
