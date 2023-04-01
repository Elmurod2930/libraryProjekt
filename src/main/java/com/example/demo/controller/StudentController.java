package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.exp.AppException;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO) {
//        return studentService.crate(studentDTO);
//        return new ResponseEntity<>("test massage", HttpStatus.OK);
//        StudentDTO response = studentService.crate(studentDTO);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//        StudentDTO response = studentService.crate(studentDTO);
//        return ResponseEntity.ok(response);
//        return ResponseEntity.ok().body(response);
//        return ResponseEntity.badRequest().build();

        try {
            StudentDTO response = studentService.crate(studentDTO);
            return ResponseEntity.ok(studentDTO);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        try {
            StudentDTO studentDTO = studentService.getById(id);
            return ResponseEntity.ok(studentService.getById(id));
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            StudentDTO studentDTO = studentService.getById(id);
            return ResponseEntity.ok(studentService.delete(id));
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO dto=studentService.getById(id);
            return ResponseEntity.ok(studentService.update(id, studentDTO));
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
