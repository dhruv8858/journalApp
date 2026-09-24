package com.TrachAcademy.journalApp.HomeController;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Entity.journalEntry;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UsersService usersService;

    @GetMapping
    public ResponseEntity<?> GetallUserJournalEntry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = usersService.findByUserName(userName);
        List<journalEntry> all = user.getJournalEntry();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<journalEntry> setEntry(@RequestBody journalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getEntryBYID(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = usersService.findByUserName(userName);
        List<journalEntry> collect = user.getJournalEntry().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<journalEntry> Jornal = journalEntryService.getEntryById(myId);
            if (Jornal.isPresent()) {
                return new ResponseEntity<>(Jornal.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = journalEntryService.DeleteByID(myId, userName);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody journalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = usersService.findByUserName(userName);
        List<journalEntry> collect = user.getJournalEntry().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<journalEntry> journal = journalEntryService.getEntryById(myId);
            if (journal.isPresent()) {
                journalEntry old = journal.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntryForPut(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }







}