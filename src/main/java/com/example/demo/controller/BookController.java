package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.exp.AppException;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody BookDTO bookDTO) {
        try {
            BookDTO dto = bookService.create(bookDTO);
            return ResponseEntity.ok(bookDTO);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> list = bookService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable("id") Integer id) {
        BookDTO dto = bookService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.update(id, bookDTO));
    }
}
