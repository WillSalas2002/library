package com.will.library.contollers;

import com.will.library.models.Book;
import com.will.library.models.Person;
import com.will.library.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping()
    public String findAllPeople(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String save(@ModelAttribute Book book) {
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String finById(@PathVariable("id") int id, Model model,
                          @ModelAttribute("modelPerson") Person person) {
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("ownerOfBook", booksService.findOwnerOfBook(id));
        model.addAttribute("people", booksService.findAll());
        return "books/each";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")Book book) {
        booksService.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/return/{id}")
    public String returnBook(@PathVariable("id") int bookId) {
        booksService.returnBook(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/assign/{id}")
    public String assignBook(@PathVariable("id") int bookId, @ModelAttribute("modelPerson") Person person) {
        booksService.assignBook(bookId, person.getId());
        return "redirect:/books";
    }
}
