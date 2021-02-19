package cn.lhf223.word.service.impl;

import cn.lhf223.word.mapper.UserMapper;
import cn.lhf223.word.model.User;
import cn.lhf223.word.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-13 22:16
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User Login(String username, String password) {
        return userMapper.selectFirstByUsernameAndPassword(username,password);
    }

    @Override
    public void updateUserById(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer register(User loginForm) {
        return userMapper.insertSelective(loginForm);
    }
}