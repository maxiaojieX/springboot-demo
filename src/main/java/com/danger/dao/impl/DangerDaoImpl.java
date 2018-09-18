package com.danger.dao.impl;

import com.danger.bean.Danger;
import com.danger.dao.DangerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiaojie.Ma on 2018/5/9.
 */
@Component
public class DangerDaoImpl implements DangerDao {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Danger danger) {
        String sql = "INSERT INTO danger (danger.qq,danger.password) VALUES (?,?)";
        jdbcTemplate.update(sql,new Object[]{danger.getQq(),danger.getPassword()});
    }

    @Override
    public Danger get() {
        String sql = "select * from danger where id =1";
        List<Danger> dangers = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Danger>(Danger.class));
        return dangers == null?null:dangers.get(0);
    }
}
