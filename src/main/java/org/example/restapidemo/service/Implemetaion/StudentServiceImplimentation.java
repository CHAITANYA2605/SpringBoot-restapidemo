package org.example.restapidemo.service.Implemetaion;

import lombok.AllArgsConstructor;
import org.example.restapidemo.DTO.CreateStudentDTO;
import org.example.restapidemo.DTO.StudentDTO;
import org.example.restapidemo.entity.Student;
import org.example.restapidemo.repository.StudentRepo;
import org.example.restapidemo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class StudentServiceImplimentation implements StudentService {

    private StudentRepo studentR;
    private ModelMapper modelMapper;

    @Override
    public List<StudentDTO> findAll() {
        List<Student> list = studentR.findAll();

        return list.stream()
                .map(Student ->modelMapper.map(Student, StudentDTO.class))
                .toList();
    }

    @Override
    public List<StudentDTO> findbyid(int id) {
        List<Student> list= studentR.findAllById(Collections.singleton(id));
        return list.stream().map(Student ->modelMapper.map(Student,StudentDTO.class)).toList();
    }

    @Override
    public StudentDTO create(CreateStudentDTO createStudentDTO) {
        Student newstudent = modelMapper.map(createStudentDTO, Student.class);
        Student s=studentR.save(newstudent);
        return modelMapper.map(s,StudentDTO.class);
    }

    @Override
    public void deletestudentbyid(int id) {
        if(studentR.findById(id).isPresent()){
            studentR.deleteById(id);
        }
    }

    @Override
    public StudentDTO updatebyid(int id, CreateStudentDTO createStudentDTO) {
       Student s=studentR.findById(id).orElseThrow(()-> new IllegalArgumentException("no user found"));
modelMapper.map(createStudentDTO,s);
         s = studentR.save(s);
return modelMapper.map(s,StudentDTO.class);
    }

    @Override
    public StudentDTO patchupdatebyid(int id, Map<String, Object> createStudentDTO) {
        Student s=studentR.findById(id).orElseThrow(()-> new IllegalArgumentException("no user found"));
        if (createStudentDTO.containsKey("firstName")) {
            s.setFirstName((String) createStudentDTO.get("firstName"));
        }
        if (createStudentDTO.containsKey("lastName")) {
            s.setLastName((String) createStudentDTO.get("lastName"));
        }
        if (createStudentDTO.containsKey("email")) {
            s.setEmail((String) createStudentDTO.get("email"));
        }
        Student updatedStudent = studentR.save(s);

        // Convert the updated student entity to a StudentDTO and return it
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }


}
