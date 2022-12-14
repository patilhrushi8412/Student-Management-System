package com.te.student.studentcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.student.studentdto.StudentDto;
import com.te.student.studententity.Student;
import com.te.student.studentresponse.StudentResponse;
import com.te.student.studentservice.StudentService;
import static com.te.student.studentconstants.StudentConstants.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/add")
	public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentDto studentDto) {
		log.info(ADDING_STUDENT);
		Student student = service.addStudent(studentDto);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, STUDENT_ADDED_SUCCESFULLY, student),
				HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<StudentResponse> getStudent(@PathVariable int id) {
		log.info(GETTING_STUDENT_DETAILS + id);
		Student student = service.getStudent(id);
		return new ResponseEntity<StudentResponse>(
				new StudentResponse(false, GETTING_STUDENT_DETAIL_SUCCESFULLY, student), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id) {
		log.info(DELETING_STUDENT_DETAILS + id);
		service.deleteStudent(id);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, DELETE_STUDENT_SUCCESFULLY, null),
				HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<StudentResponse> getAllStudent() {
		log.info(GETTING_ALL_DETAILS);
		List<Student> student = service.getAllStudent();
		return new ResponseEntity<StudentResponse>(
				new StudentResponse(false, GETTING_ALL_STUDENT_DETAILS_SUCCESFULLY, student), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentDto studentDto) {
		log.info(UPDATING_STUDENT_DETAILS);
		Student student = service.updateStudent(studentDto);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, STUDENT_UPDATED_SUCCESFULLY, student),
				HttpStatus.OK);
	}
}
