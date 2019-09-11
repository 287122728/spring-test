/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.cache.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author crush_lee
 * @version $Id: TestCacheDomain.java, v 0.1 2019年09月06日 20:07 crush_lee Exp $
 */
@Data
@ToString
public class TestCacheDomain implements Serializable {
    String name;
    String desc;
    String groupId;
}