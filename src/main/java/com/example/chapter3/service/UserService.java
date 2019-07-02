package com.example.chapter3.service;

import com.example.chapter3.dao.LoginLogDao;
import com.example.chapter3.dao.UserDao;
import com.example.chapter3.models.LoginLog;
import com.example.chapter3.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName,String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLog.setUserId(user.getUserId());
        userDao.updateLoginInfo(user);
        loginLogDao.insertIntoLoginLog(loginLog);
    }
}
