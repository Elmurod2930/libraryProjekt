package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO crate(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        studentRepository.saveStudent(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        List<StudentEntity> entityList = studentRepository.getAll();
        List<StudentDTO> list = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public StudentDTO getById(Integer id) {
        StudentEntity entity = studentRepository.getById(id);
        StudentDTO dto = new StudentDTO();
        if (entity == null) {
            return dto;
        }
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean delete(Integer id) {
        StudentEntity entity = studentRepository.getById(id);
        if (entity == null) {
            return false;
        }
        return studentRepository.delete(entity);
    }

    public Boolean update(Integer id, StudentDTO studentDTO) {
        StudentEntity entity = studentRepository.getById(id);
        if (entity == null) {
            return null;
        }
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setPhone(studentDTO.getPhone());
        entity.setId(id);
        return studentRepository.update(entity);
    }
}
