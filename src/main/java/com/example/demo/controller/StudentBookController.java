package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.StudentBookDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.exp.AppException;
import com.example.demo.service.StudentBookService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student_book")
public class StudentBookController {
    @Autowired
    private StudentBookService studentBookService;
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentBookDTO studentBookDTO) {
        try {
            StudentBookDTO dto = studentBookService.create(studentBookDTO);
            return ResponseEntity.ok(studentBookDTO);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody StudentBookDTO studentBookDTO) {
        try {
            BookDTO dto = studentBookService.update(id, studentBookDTO);
            return ResponseEntity.ok(studentBookDTO);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentBookDTO>> getAll() {
        return ResponseEntity.ok(studentBookService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            StudentBookDTO dto = studentBookService.getById(id);
            return ResponseEntity.ok(studentBookService.getById(id));
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<?> getStudentTakenList(@PathVariable("id") Integer id) {
        try {
            StudentDTO dto = studentService.getById(id);
            return ResponseEntity.ok(studentBookService.getStudentTakenList(id));
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity<List<StudentBookDTO>> getTakenBook(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentBookService.getTakenBook(id));
    }
}
