package com.TrachAcademy.journalApp.Repository;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsersRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String name);

    void deleteByUserName(String username);
}
