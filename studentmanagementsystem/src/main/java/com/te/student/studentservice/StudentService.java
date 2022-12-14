package com.te.student.studentservice;

import java.util.List;

import com.te.student.studentdto.StudentDto;
import com.te.student.studententity.Student;

public interface StudentService {

	Student addStudent(StudentDto studentDto);

	Student getStudent(int id);

	void deleteStudent(int id);

	List<Student> getAllStudent();

	Student updateStudent(StudentDto studentDto);

}
