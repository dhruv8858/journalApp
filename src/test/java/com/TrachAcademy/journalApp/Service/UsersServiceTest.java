package com.TrachAcademy.journalApp.Service;

import com.TrachAcademy.journalApp.Entity.User;
import com.TrachAcademy.journalApp.Repository.UsersRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //help to run Journal application in background while testeing so, that compent can injected into Ioc container
//@ActiveProfiles("dev")
public class UsersServiceTest {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Disabled
    @Test
    public void TestFindByUserName(){
    assertNotNull(usersRepository.findByUserName("user1"));
    }


//    @Test
//    public void TestJounalentriesNotNull(){
//       assertTrue(!usersRepository.findByUserName("user1").getJournalEntry().isEmpty());
//    }


    @Disabled
    @ParameterizedTest
    @ValueSource(strings={
            "ram",
            "user1",
            "user2"
    })
    public void usersindbTest(String getName){
        assertNotNull(usersRepository.findByUserName(getName));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void passNewUsers(User user){
        assertTrue(usersService.saveNewEntry(user));
    }

@Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3"
            ,"2,1,5"
            ,"2,4,6"
    })
    public void test(int a,int b,int result){

        assertEquals(result,a+b);
    }
}
