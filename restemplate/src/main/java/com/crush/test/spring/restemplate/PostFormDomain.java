package com.crush.test.spring.restemplate;

import java.io.Serializable;
import java.util.Objects;

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
public class PostFormDomain implements Serializable {
    String name;
    String desc;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PostFormDomain that = (PostFormDomain) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, desc);
    }
}
