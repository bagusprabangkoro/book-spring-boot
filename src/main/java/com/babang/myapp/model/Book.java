package com.babang.myapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    UUID id;
    String title;
    String author;
    String publisher;
    Date publishedDate;
}
