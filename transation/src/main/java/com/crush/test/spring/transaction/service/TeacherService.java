package com.crush.test.spring.transaction.service;

import com.crush.test.spring.transaction.domain.Teacher;
import com.crush.test.spring.transaction.mapper.TeacherMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @date 2019/4/11
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    public void add(Teacher teacher){
        teacherMapper.insert(teacher);
    }
    public int count(){
       return teacherMapper.selectCount(new Teacher());
    }
    public void addWithException(Teacher teacher){
        add(teacher);
        throw new RuntimeException("some error !");
    }
}
