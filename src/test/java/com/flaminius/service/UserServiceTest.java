package com.flaminius.service;

import com.flaminius.domain.User;
import com.flaminius.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUsersWithUser() {
        User user = new User(1, "John Doe","coolemail@gmail.com");
        List<User> list = new ArrayList<>();
        list.add(user);

        Mockito.when(userRepository.fetchAll()).thenReturn(list);
        List<User> result = userService.getUsers();

        List<User> expectedResponse = new ArrayList<>();
        expectedResponse.add(user);
        Assertions.assertEquals(expectedResponse, result);
    }

    @Test
    void testGetUsersWithoutUsers() {
        Mockito.when(userRepository.fetchAll()).thenReturn(new ArrayList<User>());
        List<User> result = userService.getUsers();
        Assertions.assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testGetUserByIdReturnsNoUser() {
        Mockito.when(userRepository.fetchById(1)).thenReturn(null);

        User result = userService.getUserById(1);
        assertNull(result);
    }

    @Test
    void testGetUserByIdReturnsUser() {
        User user = new User(1, "John Doe","coolemail@gmail.com");
        Mockito.when(userRepository.fetchById(1)).thenReturn(user);

        User result = userService.getUserById(1);
        assertEquals(user,result);
    }

    @Test
    void testCreateUser() {
        User user = new User(1, "John Doe","coolemail@gmail.com");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.createUser(user);
        Assertions.assertEquals("John Doe", result.getName());
    }
}