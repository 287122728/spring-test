package com.crush.test.spring.transaction.service;

import com.crush.test.spring.transaction.domain.Student;
import com.crush.test.spring.transaction.domain.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Title: TODO
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 客如云
 * </p>
 *
 * @author crush_lee
 * @date 2019/4/7
 */
@Service
public class ProxyService {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    /**
     * 异常情况，同时回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void testRollBackWithException(){
        Teacher teacher=initTeacher();
        Student student=initStudent();
        teacherService.add(teacher);
        studentService.addWithException(student);
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public void notRollBack(){
        Teacher teacher=initTeacher();
        Student student=initStudent();
        teacherService.add(teacher);
        studentService.addWithException(student);
    }
    @Transactional(rollbackFor = Exception.class)
    public void testTwoTransaction(){
        studentService.addWithTransaction(initStudent());
        teacherService.addWithException(initTeacher());
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public void testTwoTransactionWithOutNotRollBack(){
        studentService.addWithTransaction(initStudent());
        teacherService.addWithException(initTeacher());
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public void testTwoTransactionWithOutNotRollBackAndInnerRollBack(){
        studentService.notRollBack(initStudent());
        teacherService.addWithException(initTeacher());
    }
    private Teacher initTeacher(){
        Teacher teacher=new Teacher();
        teacher.setName("test");
        return teacher;
    }
    private Student initStudent(){
        Student student=new Student();
        student.setName("test");
        return student;
    }
    public String info(){
        return String.format("student count [%d],teacher count[%d]",studentService.count(),teacherService.count());
    }
}
