package cn.buer.web.service;

import cn.buer.web.domain.Student;

public interface StudentService {
	Student addStudent(Student student);
	Student queryStudent(Long studentId);
}
