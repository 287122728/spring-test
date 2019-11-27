package com.crush.test.spring.feign;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

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
 * @date 2019/4/3
 */
@Data
@XmlRootElement(name ="domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostFormDomain implements Serializable {
    @XmlElement(name = "name")
    String name;
    @XmlElement(name = "desc")
    String desc;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostFormDomain that = (PostFormDomain) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, desc);
    }
}
