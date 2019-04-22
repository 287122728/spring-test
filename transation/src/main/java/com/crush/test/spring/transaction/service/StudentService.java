package com.crush.test.spring.transaction.service;

import com.crush.test.spring.transaction.domain.Student;
import com.crush.test.spring.transaction.mapper.StudentMapper;

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
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public void add(Student student){
        studentMapper.insert(student);
    }
    public int count(){
        return studentMapper.selectCount(new Student());
    }
    public void addWithException(Student student){
        add(student);
        throw new RuntimeException("some error！");
    }
    @Transactional()
    public void addWithTransaction(Student student){
        add(student);
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public void notRollBack(Student student){
        addWithException(student);
    }

}
