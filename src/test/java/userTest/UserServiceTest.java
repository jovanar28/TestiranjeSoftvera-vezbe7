package userTest;

import org.example.PasswordEncoder;
import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    private static final String PASSWORD="password";
    private static final User USER_EN=new User("en id","hash",true);
    private static final User USER_DIS=new User("dis id","dis hash",false);

    @BeforeEach
    void setup(){
        userRepository=mock(UserRepository.class);
        passwordEncoder=mock(PasswordEncoder.class);


        //mockovanje poziv bazi
        when(userRepository.findById(USER_EN.getId())).thenReturn(USER_EN);
        when(userRepository.findById(USER_DIS.getId())).thenReturn(USER_DIS);


        //bitan redosled
        when(passwordEncoder.encode(anyString())).thenReturn("invalid");
        when(passwordEncoder.encode(PASSWORD)).thenReturn("hash");

        userService=new UserService(userRepository,passwordEncoder);

    }


    @Test
    void validForValidCredentials(){
        boolean isValid= userService.isValidUser(USER_EN.getId(),PASSWORD);
        assertTrue(isValid);

        verify(userRepository).findById(USER_EN.getId());
        verify(passwordEncoder).encode(PASSWORD);

    }
    @Test
    void invalidForInvalidId(){
        boolean isValid=userService.isValidUser("id",PASSWORD);
        assertFalse(isValid);

        verify(userRepository).findById("id");
        //never jer je id pogresan za userrepo pa nikad nece ni pozvati
        //passwordencoder
        verify(passwordEncoder,never()).encode(PASSWORD);
    }
    @Test
    void invalidForInvalidPassword(){
        boolean isValid=userService.isValidUser(USER_EN.getId(),"sifra");
        assertFalse(isValid);

        verify(userRepository).findById(USER_EN.getId());
        verify(passwordEncoder).encode("sifra");
    }
    @Test
    void invalidForDisabledUser(){
        boolean isValid=userService.isValidUser(USER_DIS.getId(),PASSWORD);
        assertFalse(isValid);

        verify(userRepository).findById(anyString());
        verify(passwordEncoder,never()).encode(PASSWORD);
    }


}
