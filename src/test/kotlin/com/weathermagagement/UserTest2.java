package com.weathermagagement;

import com.weathermagagement.domain.repository.UserRepository;
import com.weathermagagement.dto.ResponseLoginDto;
import com.weathermagagement.dto.SignupRequest;
import com.weathermagagement.security.service.AuthService;
import com.weathermagagement.service.UserService;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserTest2 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @AfterEach
    public void deleteAll() {
        userRepository.deleteAllInBatch(userRepository.findAll());
    }


    @Test
    public void loginTest() throws Exception {
        SignupRequest signupRequest = new SignupRequest("test", "test", "test");
        userService.signUp(signupRequest);

        ResponseLoginDto authenticate = authService.authenticate("test", "test");
        System.out.println("authenticate = " + authenticate);
        Assertions.assertThat(authenticate.getUsername()).isEqualTo("test");
    }

}