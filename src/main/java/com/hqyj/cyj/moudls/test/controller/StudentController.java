package com.hqyj.cyj.moudls.test.controller;

import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.entity.Student;
import com.hqyj.cyj.moudls.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 127.0.0.1/api/student --post
     * {"StudentName":"student1","StudentCard":{"CardId":"1"}}
     * */
    @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }

    /**
     * 127.0.0.1/api/student/5 --post
     * */
    @GetMapping("/student/{studentId}")
    public Student getStudentByStudentId(int studentId){
        return studentService.getStudentByStudentId(studentId);
    }

    /**
     * 127.0.0.1/api/student --post
     * {"currentPage":"1","pageSize":"5","keyWord":"student2","orderBy":"studentName","sort":"desc"}
     * */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentBySearchVo(SearchVo searchVo){
        return studentService.getStudentBySearchVo(searchVo);
    }
}
