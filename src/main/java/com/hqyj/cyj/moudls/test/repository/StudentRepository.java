package com.hqyj.cyj.moudls.test.repository;

import com.hqyj.cyj.moudls.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
