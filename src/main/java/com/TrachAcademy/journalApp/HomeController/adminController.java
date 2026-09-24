package com.TrachAcademy.journalApp.HomeController;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    UsersService usersService;

    @GetMapping
    ResponseEntity<?> getAllUsers(){
        List<User> all=usersService.getAll();

        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public  void CreateAdminUser(@RequestBody User user){
        usersService.SaveUserASadmin(user);
}




}
