package com.danger.bean;

import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.Ma on 2018/9/18.
 */
@Component
public interface DangerMapper {

    Danger selectByPrimaryKey(Integer id);

    void insert();

    void insert2();
}
