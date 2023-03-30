package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.StudentBookDTO;
import com.example.demo.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student_book")
public class StudentBookController {
    @Autowired
    private StudentBookService studentBookService;

    @PostMapping(value = "/create")
    public StudentBookDTO create(@RequestBody StudentBookDTO studentBookDTO) {
        return studentBookService.create(studentBookDTO);
    }

    @PutMapping(value = "/update/{id}")
    public BookDTO update(@PathVariable("id") String id, @RequestBody StudentBookDTO studentBookDTO) {
        return studentBookService.update(id, studentBookDTO);
    }

    @GetMapping(value = "/list")
    public List<StudentBookDTO> getAll() {
        return studentBookService.getAll();
    }

    @GetMapping(value = "/get/{id}")
    public StudentBookDTO getById(@PathVariable("id") String id) {
        return studentBookService.getById(id);
    }

    @GetMapping(value = "/student/{id}")
    public List<BookDTO> getStudentTakenList(@PathVariable("id") Integer id) {
        return studentBookService.getStudentTakenList(id);
    }

    @GetMapping(value = "/book/{id}")
    public List<StudentBookDTO> getTakenBook(@PathVariable("id") Integer id) {
        return studentBookService.getTakenBook(id);
    }
}
