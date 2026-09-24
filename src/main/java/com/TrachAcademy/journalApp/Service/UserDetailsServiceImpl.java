package com.TrachAcademy.journalApp.Service;


import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= usersRepository.findByUserName(username);

        if(user !=null) {
           return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                     .roles(user.getRoles().toArray( new String[0]))
                    .build();


        }
        throw  new UsernameNotFoundException("Username dont exists :"+username);
        }
}
