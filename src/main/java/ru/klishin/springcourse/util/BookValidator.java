package ru.klishin.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.klishin.springcourse.models.Book;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (!Character.isUpperCase(book.getTitle().codePointAt(0)))
            errors.rejectValue("title", "", "Title should start with a capital letter");

        if (!Character.isUpperCase(book.getAuthor().codePointAt(0)))
            errors.rejectValue("author", "", "The author's name should start with a capital letter");
    }
}
