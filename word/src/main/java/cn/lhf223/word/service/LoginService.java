package cn.lhf223.word.service;

import cn.lhf223.word.model.User;

public interface LoginService {
    User Login(String username, String password);

    void updateUserById(User user);

    Integer register(User loginForm);
}
