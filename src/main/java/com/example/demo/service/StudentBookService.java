package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.StudentBookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.StudentBookEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentBookRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentBookService {
    @Autowired
    private StudentBookRepository studentBookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentBookDTO create(StudentBookDTO studentBookDTO) {
        StudentBookEntity entity = new StudentBookEntity();
        entity.setBookId(studentBookDTO.getBookId());
        entity.setStudentId(studentBookDTO.getStudentId());
        entity.setReturnedDate(studentBookDTO.getReturnedDate());
        studentBookRepository.create(entity);
        return studentBookDTO;
    }

    public BookDTO update(String id, StudentBookDTO studentBookDTO) {
        StudentBookEntity entity = studentBookRepository.getById(id);
        BookDTO dto = new BookDTO();
        if (entity == null) {
            return dto;
        }
        entity.setBookId(studentBookDTO.getBookId());
        entity.setStudentId(studentBookDTO.getStudentId());
        entity.setReturnedDate(studentBookDTO.getReturnedDate());
        studentBookRepository.update(entity);
        BookEntity bookEntity = bookRepository.getById(entity.getBookId());
        dto.setId(bookEntity.getId());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setTitle(bookEntity.getTitle());
        dto.setPublishYear(bookEntity.getPublishYear());
        return dto;
    }

    public List<StudentBookDTO> getAll() {
        List<StudentBookEntity> entityList = studentBookRepository.getAll();
        List<StudentBookDTO> list = new LinkedList<>();
        for (StudentBookEntity entity : entityList) {
            StudentBookDTO dto = new StudentBookDTO();
            dto.setBookId(entity.getBookId());
            dto.setStudentId(entity.getStudentId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setReturnedDate(entity.getReturnedDate());
            dto.setDuration(entity.getDuration());
            dto.setStatus(entity.getStatus());
            list.add(dto);
        }
        return list;
    }

    public StudentBookDTO getById(String id) {
        StudentBookEntity entity = studentBookRepository.getById(id);
        StudentBookDTO dto = new StudentBookDTO();
        if (entity == null) {
            return dto;
        }
        dto.setBookId(entity.getBookId());
        dto.setStudentId(entity.getStudentId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setReturnedDate(entity.getReturnedDate());
        dto.setDuration(entity.getDuration());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<BookDTO> getStudentTakenList(Integer studentId) {
        StudentEntity student = studentRepository.getById(studentId);
        if (student == null) {
            return new LinkedList<>();
        }
        List<Integer> bookIdList = studentBookRepository.getBookById(studentId);
        List<BookDTO> bookDTOList = new LinkedList<>();
        for (Integer bookId : bookIdList) {
            BookEntity book = bookRepository.getById(bookId);
            BookDTO dto = new BookDTO();
            if (book == null) {
                continue;
            }
            dto.setId(book.getId());
            dto.setAuthor(book.getAuthor());
            dto.setTitle(book.getTitle());
            dto.setPublishYear(book.getPublishYear());
            bookDTOList.add(dto);
        }
        return bookDTOList;
    }

    public List<StudentBookDTO> getTakenBook(Integer bookId) {
        List<StudentBookEntity> entityList = studentBookRepository.getTakenBookById(bookId);
        List<StudentBookDTO> list = new LinkedList<>();
        for (StudentBookEntity entity : entityList) {
            StudentBookDTO dto = new StudentBookDTO();
            dto.setBookId(entity.getBookId());
            dto.setStudentId(entity.getStudentId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setReturnedDate(entity.getReturnedDate());
            dto.setDuration(entity.getDuration());
            dto.setStatus(entity.getStatus());
            list.add(dto);
        }
        return list;
    }
}
