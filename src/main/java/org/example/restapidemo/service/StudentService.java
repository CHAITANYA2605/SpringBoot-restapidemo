package org.example.restapidemo.service;

import org.example.restapidemo.DTO.CreateStudentDTO;
import org.example.restapidemo.DTO.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDTO> findAll();
    List<StudentDTO> findbyid(int id);

    StudentDTO create(CreateStudentDTO createStudentDTO);

    void deletestudentbyid(int id);

    StudentDTO updatebyid(int id, CreateStudentDTO createStudentDTO);

    StudentDTO patchupdatebyid(int id, Map<String, Object> createStudentDTO);
}
