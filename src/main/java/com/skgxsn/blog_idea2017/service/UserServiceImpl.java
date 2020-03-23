package com.skgxsn.blog_idea2017.service;

import com.skgxsn.blog_idea2017.dao.UserRepository;
import com.skgxsn.blog_idea2017.po.User;
import com.skgxsn.blog_idea2017.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
