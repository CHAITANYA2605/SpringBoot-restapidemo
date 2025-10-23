package org.example.restapidemo.controller;
import lombok.AllArgsConstructor;
import org.example.restapidemo.DTO.CreateStudentDTO;
import org.example.restapidemo.DTO.StudentDTO;
import org.example.restapidemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class studentcontroller {

    private StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentDTO>> student(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StudentDTO>> studentbyid(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findbyid(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody CreateStudentDTO CreateStudentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(CreateStudentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        studentService.deletestudentbyid(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody CreateStudentDTO CreateStudentDTO,@PathVariable int id){
        return ResponseEntity.ok(studentService.updatebyid(id,CreateStudentDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> patchupdate(@RequestBody Map<String, Object> CreateStudentDTO, @PathVariable int id){
        return ResponseEntity.ok(studentService.patchupdatebyid(id,CreateStudentDTO));
    }
}
