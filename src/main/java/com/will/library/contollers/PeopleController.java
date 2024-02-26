package com.will.library.contollers;

import com.will.library.dao.PersonDAO;
import com.will.library.models.Book;
import com.will.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String findAllPeople(Model model) {
        model.addAttribute("people", personDAO.findAll());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String save(@ModelAttribute Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.findById(id));
        return "people/edit";
    }

    @GetMapping("/{id}")
    public String finById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.findById(id));
        model.addAttribute("booksOfPerson", personDAO.findBooksOfPerson(id));
        return "people/each";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")Person person) {
        personDAO.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
