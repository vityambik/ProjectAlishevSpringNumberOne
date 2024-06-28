package ru.klishin.springcourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 6, max = 150, message = "Name should be between 6 and 150 characters")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+$", message = "Your full name should be in this format: Surname Name Patronymic")
    private String full_name;

    //@NotEmpty(message = "Year of birth should not be empty")
    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    @Max(value = 2016, message = "Year of birth must be less than 2017")
    private int year_of_birth;
}
