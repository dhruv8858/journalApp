package com.TrachAcademy.journalApp.HomeController;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UsersService usersService;

    @PostMapping("/newUser")
    public void CreateUser(@RequestBody User user){
        usersService.saveNewEntry(user);
    }
}
