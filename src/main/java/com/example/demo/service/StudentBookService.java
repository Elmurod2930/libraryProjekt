package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.StudentBookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.StudentBookEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.exp.AppException;
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
        if (studentBookDTO.getBookId() == null) {
            throw new AppException("book id qani?");
        }
        if (studentBookDTO.getStudentId() == null) {
            throw new AppException("student id qani?");
        }
        BookEntity book = bookRepository.getById(studentBookDTO.getBookId());
        if (book == null) {
            throw new AppException("bunaqa idli kitob yo'q");
        }
        StudentEntity student = studentRepository.getById(studentBookDTO.getBookId());
        if (student == null) {
            throw new AppException("bunaqa idli student yo'q");
        }
        StudentBookEntity entity = new StudentBookEntity();
        entity.setBook(book);
        entity.setStudent(student);
        studentBookRepository.create(entity);
        return studentBookDTO;
    }

    public BookDTO update(String id, StudentBookDTO studentBookDTO) {
        StudentBookEntity entity = studentBookRepository.getById(id);
        BookDTO dto = new BookDTO();
        if (entity == null) {
            throw new AppException("bunaqa idli student_book yo'q");
        }
        BookEntity book = bookRepository.getById(studentBookDTO.getBookId());
        if (book == null) {
            throw new AppException("bunaqa idli kitob yo'q");
        }
        StudentEntity student = studentRepository.getById(studentBookDTO.getStudentId());
        if (student == null) {
            throw new AppException("bunaqa idli student yo'q");
        }
        entity.setBook(book);
        entity.setStudent(student);
        studentBookRepository.update(entity);
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setTitle(book.getTitle());
        dto.setPublishYear(book.getPublishYear());
        return dto;
    }

    public List<StudentBookDTO> getAll() {
        List<StudentBookEntity> entityList = studentBookRepository.getAll();
        return getStudentBookDTO(entityList);
    }

    public StudentBookDTO getById(String id) {
        StudentBookEntity entity = studentBookRepository.getById(id);
        StudentBookDTO dto = new StudentBookDTO();
        dto.setBookId(entity.getBook().getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setReturnedDate(entity.getReturnedDate());
        dto.setDuration(entity.getDuration());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<BookDTO> getStudentTakenList(Integer studentId) {
        // TODO: 4/2/2023
        StudentEntity student = studentRepository.getById(studentId);
        if (student == null) {
            throw new AppException("bunaqa idli student yo'q");
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
        // TODO: 4/2/2023
        List<StudentBookEntity> entityList = studentBookRepository.getTakenBookById(bookId);
        return getStudentBookDTO(entityList);
    }

    public List<StudentBookDTO> getStudentBookDTO(List<StudentBookEntity> entityList) {
        List<StudentBookDTO> list = new LinkedList<>();
        for (StudentBookEntity entity : entityList) {
            StudentBookDTO dto = new StudentBookDTO();
            dto.setId(entity.getId());
            dto.setBookId(entity.getBook().getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setReturnedDate(entity.getReturnedDate());
            dto.setDuration(entity.getDuration());
            dto.setStatus(entity.getStatus());
            list.add(dto);
        }
        return list;
    }
}
