package com.example.chapter3.dao;

import com.example.chapter3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private static final String MATCH_COUNT_SQL=
            "select count(*) from user where user_name = ? and password=?";
    private static final String FIND_USER_BY_USERNAME_SQL =
            "select user_id,credits from user where user_name = ?";
    private static final String UPDATE_LOGIN_INFO_SQL =
            "update user set last_visit=?,last_ip=?,credits=? where user_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName,String password){
        int count = jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},int.class);
        return count;
    }

    public User findUserByUserName(String userName){
        User user = new User();
        jdbcTemplate.query(FIND_USER_BY_USERNAME_SQL,new Object[]{userName},rch->{
            user.setCredits(rch.getInt("credits"));
            user.setUserId(rch.getInt("user_id"));
            user.setUserName(userName);
        });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
