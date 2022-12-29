package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByName(String username) {
        Optional<User> users = userRepository.findByUsername(username);

        User user = users.get();

        if(user == null) throw new UsernameNotFoundException("User not found");


        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
