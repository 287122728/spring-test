package com.crush.test.spring.transaction.mapper;

import com.crush.test.spring.transaction.BaseMapper;
import com.crush.test.spring.transaction.domain.Student;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
