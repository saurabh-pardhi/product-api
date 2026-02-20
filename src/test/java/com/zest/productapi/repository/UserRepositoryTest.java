//package com.zest.productapi.repository;
//
//import com.zest.productapi.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
//
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void testFindByUsername() {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("encryptedPassword");
//        userRepository.save(user);
//
//        Optional<User> found = userRepository.findByUsername("admin");
//        assertTrue(found.isPresent());
//        assertEquals("admin", found.get().getUsername());
//    }
//}