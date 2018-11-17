package com.fang.demo.service;

import com.fang.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {

    List<User> findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);

}
