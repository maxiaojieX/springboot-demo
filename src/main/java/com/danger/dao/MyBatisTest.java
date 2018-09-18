package com.danger.dao;

import com.danger.bean.Danger;
import com.danger.bean.DangerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.Ma on 2018/9/18.
 */
@Component
public class MyBatisTest {

    @Autowired
    private DangerMapper dangerMapper;

    public Danger find() {

        return dangerMapper.selectByPrimaryKey(1);
    }

}
