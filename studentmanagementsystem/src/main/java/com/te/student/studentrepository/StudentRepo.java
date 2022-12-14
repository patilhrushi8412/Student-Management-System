package com.te.student.studentrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.student.studententity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Optional<Student> findByStudentEmail(String studentEmail);

}
