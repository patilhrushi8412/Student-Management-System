package com.te.student.studentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.student.studentcustomexception.StudentCustomException;
import com.te.student.studentdto.StudentDto;
import com.te.student.studententity.Student;
import com.te.student.studentrepository.StudentRepo;
import static com.te.student.studentconstants.StudentConstants.*;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo repo;

	@Override
	public Student addStudent(StudentDto studentDto) {
		try {
			Optional<Student> findByStudent = repo.findByStudentEmail(studentDto.getStudentEmail());
			if (findByStudent.isPresent()) {
				log.error(STUDENT_WHICH_YOU_WANT_TO_ADD_IS_ALLREADY_PRESENT);
				throw new StudentCustomException(STUDENT_WHICH_YOU_WANT_TO_ADD_IS_ALLREADY_PRESENT);
			} else {
				Student student = new Student();
				BeanUtils.copyProperties(studentDto, student);
				log.info(STUDENT_ADDED_SUCCESFULLY);
				Student save = repo.save(student);
				return save;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Student getStudent(int id) {
		try {
			Optional<Student> student = repo.findById(id);
			if (!student.isPresent()) {
				log.error(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
				throw new StudentCustomException(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
			} else {
				log.info(GETTING_STUDENT_DETAILS_SUCCESFULLY);
				return student.get();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteStudent(int id) {
		try {
			Optional<Student> student = repo.findById(id);
			if (!student.isPresent()) {
				log.error(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
				throw new StudentCustomException(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
			} else {
				log.info(DELETING_STUDENT_DETAILS_SUCCESFULLY);
				repo.delete(student.get());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> getAllStudent() {
		try {
			List<Student> studentList = repo.findAll();
			if (studentList.isEmpty()) {
				log.error(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
				throw new StudentCustomException(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
			} else {
				log.info(GETTING_ALL_STUDENT_DETAILS_SUCCESFULLY);
				return studentList;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Student updateStudent(StudentDto studentDto) {
		try {
			Optional<Student> student = repo.findByStudentEmail(studentDto.getStudentEmail());
			if (!student.isPresent()) {
				log.error(STUDENT_IS_NOT_PRESENT_WHICH_YOU_WANT_TO_UPDATE);
				throw new StudentCustomException(STUDENT_IS_NOT_PRESENT_WHICH_YOU_WANT_TO_UPDATE);
			} else {
				BeanUtils.copyProperties(studentDto, student.get());
				Student save = repo.save(student.get());
				log.info(UPDATE_STUDENT_DETAILS_SUCCESFULLY);
				return save;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
