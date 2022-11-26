package com.weathermagagement.security.service;

import com.weathermagagement.domain.User;
import com.weathermagagement.domain.repository.UserRepository;
import com.weathermagagement.dto.ResponseLoginDto;
import com.weathermagagement.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseLoginDto authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("유저가 없습니다.");
        }
        matchPassword(password, user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = tokenProvider.createToken(authenticate);
        return new ResponseLoginDto(username, token);
    }

    private void matchPassword(String password, User user) {
        boolean matchs = passwordEncoder.matches(password, user.getPassword());

        if (!matchs) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
    }
}
