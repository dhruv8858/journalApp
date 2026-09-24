package com.TrachAcademy.journalApp.Service;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UsersRepository usersRepository;

    //helps @Mocks to inject in @InjectMocks && also to initialize userRepository
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }


//    @Disabled
//    @Test
//    public void loadUserByUsernameTest(){
// when(usersRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("Pas").roles(new ArrayList<>()).build());
//        UserDetails user=userDetailsService.loadUserByUsername("Sham");
//
//        Assertions.assertNotNull(user);
//
//    }

}
