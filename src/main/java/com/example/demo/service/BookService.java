package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.exp.AppException;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDTO create(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setPublishYear(dto.getPublishYear());
        if (dto.getAuthor() == null || dto.getAuthor().isBlank()){
            throw new AppException("author qani ?");
        }
        if (dto.getTitle() == null || dto.getTitle().isBlank()){
            throw new AppException("title qani ?");
        }
        if (dto.getPublishYear() == null){
            throw new AppException("publish year qani ?");
        }

        bookRepository.saveStudent(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<BookDTO> getAll() {
        List<BookEntity> entityList = bookRepository.getAll();
        List<BookDTO> list = new LinkedList<>();
        for (BookEntity entity : entityList) {
            BookDTO dto = new BookDTO();
            dto.setId(entity.getId());
            dto.setAuthor(entity.getAuthor());
            dto.setTitle(entity.getTitle());
            dto.setPublishYear(entity.getPublishYear());
            list.add(dto);
        }
        return list;
    }

    public BookDTO getById(Integer id) {
        BookEntity entity = bookRepository.getById(id);
        BookDTO dto = new BookDTO();
        if (entity == null) {
            return dto;
        }
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setTitle(entity.getTitle());
        dto.setPublishYear(entity.getPublishYear());
        return dto;
    }

    public Boolean delete(Integer id) {
        BookEntity entity = bookRepository.getById(id);
        if (entity == null) {
            return false;
        }
        return bookRepository.delete(entity);
    }

    public Boolean update(Integer id, BookDTO dto) {
        BookEntity entity = bookRepository.getById(id);
        if (entity == null) {
            return null;
        }
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setPublishYear(dto.getPublishYear());
        entity.setId(id);
        return bookRepository.update(entity);
    }
}
