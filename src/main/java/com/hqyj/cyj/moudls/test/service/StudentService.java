package com.hqyj.cyj.moudls.test.service;

import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(int studentId);

    Page<Student> getStudentBySearchVo(SearchVo searchVo);
}
