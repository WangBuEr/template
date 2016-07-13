package cn.buer.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.buer.util.aspect.annotation.ErrorHandling;
import cn.buer.web.domain.Student;
import cn.buer.web.persistence.StudentDao;
import cn.buer.web.service.StudentService;
@Service
@Transactional(rollbackFor = Exception.class)
@ErrorHandling
@Component
public class StudentServiceImpl implements StudentService {
	@Resource
	private StudentDao studentDao;
	@Override
	public Student addStudent(Student student) {
		studentDao.insert(student);
		try {
			System.out.println("新增学生" + student.getName());
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return student;
	}
	@ErrorHandling("查询学生信息")
	@Override
	public Student queryStudent(Long studentId) {
		Student student = studentDao.selectByPrimaryKey(studentId);
		return student;
		
	}

}
