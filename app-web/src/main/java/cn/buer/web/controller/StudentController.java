package cn.buer.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buer.web.domain.Student;
import cn.buer.web.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {
	@Resource
	private StudentService studentService;
	@RequestMapping("queryStudent/{studentId}")
	@ResponseBody
	public Student queryStudent(@PathVariable Long studentId){
		Student result =  studentService.queryStudent(studentId);
		return result;
	}
}
