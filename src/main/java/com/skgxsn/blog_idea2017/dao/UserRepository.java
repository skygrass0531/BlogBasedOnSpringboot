package com.skgxsn.blog_idea2017.dao;

import com.skgxsn.blog_idea2017.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
