/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.sharding.jdbc.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author crush_lee
 * @version $Id: ShardingDomain.java, v 0.1 2019年08月07日 16:59 crush_lee Exp $
 */
@Data
@Table(name="sharding_domain")
public class ShardingDomain {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="biz_id")
    private Integer bizId;
    @Column(name="content")
    private String  content;
}