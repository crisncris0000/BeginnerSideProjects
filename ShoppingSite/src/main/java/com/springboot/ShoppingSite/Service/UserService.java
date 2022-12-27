package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.User;

public interface UserService {

    public User findUserByName(String username);

}
