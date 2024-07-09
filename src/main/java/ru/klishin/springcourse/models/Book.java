package ru.klishin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int book_id;
    private Integer person_id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 1, max = 100, message = "Author should be between 1 and 100 characters")
    private String author;

    @Min(value = 1457, message = "Year of publishing should be greater than 1457")
    @Max(value = 2024, message = "Year of publishing must be less than 2025")
    private int year_of_publishing;
}
