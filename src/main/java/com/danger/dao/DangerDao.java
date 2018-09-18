package com.danger.dao;

import com.danger.bean.Danger;

/**
 * Created by xiaojie.Ma on 2018/5/9.
 */
public interface DangerDao {

    void save(Danger danger);

    Danger get();

}
