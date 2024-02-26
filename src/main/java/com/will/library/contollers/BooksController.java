package com.will.library.contollers;

import com.will.library.dao.BookDAO;
import com.will.library.dao.PersonDAO;
import com.will.library.models.Book;
import com.will.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String findAllPeople(Model model) {
        model.addAttribute("books", bookDAO.findAll());
        return "books/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String save(@ModelAttribute Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.findById(id));
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String finById(@PathVariable("id") int id, Model model,
                          @ModelAttribute("modelPerson") Person person) {
        model.addAttribute("book", bookDAO.findById(id));
        model.addAttribute("ownerOfBook", bookDAO.findOwnerOfBook(id));
        model.addAttribute("people", personDAO.findAll());
        return "books/each";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")Book book) {
        bookDAO.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/return/{id}")
    public String returnBook(@PathVariable("id") int bookId) {
        bookDAO.returnBook(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/assign/{id}")
    public String assignBook(@PathVariable("id") int bookId, @ModelAttribute("modelPerson") Person person) {
        bookDAO.assignBook(bookId, person.getId());
        return "redirect:/books";
    }
}
