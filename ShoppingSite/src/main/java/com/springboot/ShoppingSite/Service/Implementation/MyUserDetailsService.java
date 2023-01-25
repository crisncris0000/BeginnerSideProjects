package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.UserRepository;
import com.springboot.ShoppingSite.Service.Implementation.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> users = userRepository.findByUsername(username);

       User user = users.get();

       if(user == null){
           throw new UsernameNotFoundException("User not found");
       }

       return new MyUserDetails(user);
    }
}
