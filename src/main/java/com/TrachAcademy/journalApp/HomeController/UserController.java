package com.TrachAcademy.journalApp.HomeController;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Entity.journalEntry;
import com.TrachAcademy.journalApp.Repository.UsersRepository;
import com.TrachAcademy.journalApp.Service.JournalEntryService;
import com.TrachAcademy.journalApp.Service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UsersService usersService;

@Autowired
    UsersRepository usersRepository;


    @PutMapping
    public ResponseEntity<?> UpdateUser(@RequestBody User user){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
                 String userName=authentication.getName();
                User NameInDB=  usersService.findByUserName(userName);
            NameInDB.setUserName(user.getUserName());
            NameInDB.setPassword(user.getPassword());
            usersService.saveNewEntry(NameInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @DeleteMapping
    public ResponseEntity<?> UpdateUser(){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

           usersRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
