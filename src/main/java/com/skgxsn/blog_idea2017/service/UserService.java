package com.skgxsn.blog_idea2017.service;

import com.skgxsn.blog_idea2017.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
