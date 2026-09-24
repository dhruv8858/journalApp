package com.TrachAcademy.journalApp.Service;

//startd way to do
//controller -> service -> repositry

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

//    private static final Logger logger= LoggerFactory.getLogger(UsersService.class);

    public boolean saveNewEntry(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("user"));
            usersRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("ERROR: saveNewEntry not worked for ->{}",user.getUserName(),e);
//            logger.info("please correct your method in UserService !!!!");
              log.info("---------------!");
               log.error("!!------------");
              log.warn("p--------");
                log.debug("--------- ");
            log.trace("-----");

        return false;
        }
    }

    public void SaveUserASadmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("user","Admin"));
        usersRepository.save(user);

    }


    public void saveEntry(User entry){
        usersRepository.save(entry);

    }
    public List<User> getAll(){
       return usersRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId myid){
        return usersRepository.findById(myid);
    }

    public void DeleteByID(ObjectId id){
        usersRepository.deleteById(id);
    }

    public User findByUserName(String name ){
        return  usersRepository.findByUserName(name);
    }

}
