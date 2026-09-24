package com.TrachAcademy.journalApp.Service;

//startd way to do
//controller -> service -> repositry

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Entity.journalEntry;
import com.TrachAcademy.journalApp.Repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;
@Autowired
UsersService usersService;


@Transactional
    public void saveEntry(journalEntry entry, String userName){
       try{
           User user=usersService.findByUserName(userName);
           journalEntry saved=journalEntryRepository.save(entry);
           user.getJournalEntry().add(saved);
//           user.setUserName(null);
           usersService.saveEntry(user);
       } catch (Exception e) {
           System.out.println(e);

           throw new RuntimeException("An error accured while save entry",e);
       }


    }

    public void saveEntryForPut(journalEntry entry){

      journalEntryRepository.save(entry);
    }

    public List<journalEntry> getAll(){
       return journalEntryRepository.findAll();
    }

    public Optional<journalEntry> getEntryById(ObjectId myid){
        return journalEntryRepository.findById(myid);
    }

    @Transactional
    public boolean DeleteByID(ObjectId id, String userName){
    boolean removedStatus=false;

        try {
    User user = usersService.findByUserName(userName);
             removedStatus = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
            if (removedStatus) {
                usersService.saveEntry(user);
                journalEntryRepository.deleteById(id);

            }
        } catch (Exception e) {
         log.error("Error (findByUserName Method ): ",e);
        throw new RuntimeException("error accured while deleteing some entry",e);
        }
return removedStatus;
    }





}
