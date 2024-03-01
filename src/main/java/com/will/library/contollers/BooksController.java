package com.will.library.contollers;

import com.will.library.models.Book;
import com.will.library.models.Person;
import com.will.library.services.BooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String findAllPeople(@RequestParam(name = "sort_by_year", required = false) String sort,
                                @RequestParam(name = "page", required = false) String page,
                                @RequestParam(name = "books_per_page", required = false) String itemsPerPage,
                                Model model) {
        if (page != null && itemsPerPage != null && sort != null) {
            model.addAttribute("books", booksService.findAll(Integer.parseInt(page), Integer.parseInt(itemsPerPage), sort));
        } else if (page != null && itemsPerPage != null) {
            model.addAttribute("books", booksService.findAll(Integer.parseInt(page), Integer.parseInt(itemsPerPage)));
        } else if (sort != null && sort.equals("true")) {
            model.addAttribute("books", booksService.findAll("year"));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model,
                          @ModelAttribute("modelPerson") Person person) {
        Book book = booksService.findById(id);
        System.out.println("Inside getMapping()" + book.getOwner());
        model.addAttribute("book", book);
        model.addAttribute("ownerOfBook", booksService.findOwnerOfBook(id));
        model.addAttribute("people", booksService.findAllPeople());
        return "books/each";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
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
    public String assignBook(@PathVariable("id") int bookId,
                             @ModelAttribute("modelPerson") Person person) {
        booksService.assignBook(bookId, person.getId());
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "book-name", required = false) String bookName, Model model) {
        model.addAttribute("books", booksService.search(bookName));
        return "books/search";
    }
}
