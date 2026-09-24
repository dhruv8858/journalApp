package com.TrachAcademy.journalApp.Service;


import com.TrachAcademy.journalApp.Entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().userName("user6").password("user6").build()),
//                Arguments.of(User.builder().userName("user7").password("").build())
//       );
//    }
}
