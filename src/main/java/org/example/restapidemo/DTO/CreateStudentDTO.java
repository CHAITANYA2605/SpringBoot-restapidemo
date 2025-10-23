package org.example.restapidemo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStudentDTO {
    private String firstName;
    private String lastName;
    private String email;
}
