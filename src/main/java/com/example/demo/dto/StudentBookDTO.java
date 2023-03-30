package com.example.demo.dto;

import com.example.demo.entity.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class StudentBookDTO {
    private UUID id;
    private Integer studentId;
    private Integer bookId;
    private Timestamp createdDate;
    private StudentStatus status;
    private Timestamp returnedDate;
    private Integer duration;
}
