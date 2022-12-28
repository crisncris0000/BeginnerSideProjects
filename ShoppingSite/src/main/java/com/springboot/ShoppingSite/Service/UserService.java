package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public User findUserByName(String username);

}
