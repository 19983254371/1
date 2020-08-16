package com.hqyj.cyj.moudls.test.service.Impl;

import com.hqyj.cyj.moudls.common.vo.Result;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.entity.Student;
import com.hqyj.cyj.moudls.test.repository.StudentRepository;
import com.hqyj.cyj.moudls.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,"Insert success",student);
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
        Sort.Direction direction=StringUtils.isBlank(searchVo.getSort())||
                searchVo.getSort().equalsIgnoreCase("asc")?
                Sort.Direction.ASC :Sort.Direction.DESC;
            String orderBy =  StringUtils.isBlank(searchVo.getOrderBy()) ?
                    "student_id":searchVo.getOrderBy();
            Sort sort = Sort.by(direction,orderBy);
        Pageable pageable = PageRequest.of(
                searchVo.getCurrentPage()-1,
                searchVo.getPageSize(),
                sort);
        Student student =new Student();
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("studentName",match -> match.contains())
                .withIgnorePaths("studentId");
        Example<Student> example = Example.of(student,matcher);
//        studentRepository.findAll();
        return studentRepository.findAll(example,pageable);
    }
}
