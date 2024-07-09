package ru.klishin.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.klishin.springcourse.dao.BookDAO;
import ru.klishin.springcourse.dao.PersonDAO;
import ru.klishin.springcourse.models.Book;
import ru.klishin.springcourse.models.Person;
import ru.klishin.springcourse.util.BookValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class LibraryController {

    private final BookDAO bookDAO;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;

    @Autowired
    public LibraryController(BookDAO bookDAO, BookValidator bookValidator, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("library", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {

        if(bookDAO.show(id).getPerson_id() != null)
            model.addAttribute("reader", personDAO.show(bookDAO.show(id).getPerson_id()));

        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/appoint")
    public String appoint(@ModelAttribute("person") Person person, @PathVariable("id") int book_id) {
        bookDAO.appoint(person.getPerson_id(), book_id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/freeing")
    public String freeingBook(@PathVariable("id") int book_id) {
        bookDAO.freeingBook(book_id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
