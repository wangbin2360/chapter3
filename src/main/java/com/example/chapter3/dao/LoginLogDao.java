package com.example.chapter3.dao;

import com.example.chapter3.models.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_LOG_SQL =
            "insert into login_log(user_id,ip,login_datetime) values(?,?,?)";

    public void insertIntoLoginLog(LoginLog loginLog){
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,new Object[]{loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()});
    }
}
