package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/create")
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @GetMapping("/list")
    public List<BookDTO> getAll() {
        List<BookDTO> list = bookService.getAll();
        return list;
    }

    @GetMapping(value = "/get/{id}")
    public BookDTO getById(@PathVariable("id") Integer id) {
        return bookService.getById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean delete(@PathVariable("id") Integer id) {
        return bookService.delete(id);
    }

    @PutMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO) {
        return bookService.update(id, bookDTO);
    }
}
