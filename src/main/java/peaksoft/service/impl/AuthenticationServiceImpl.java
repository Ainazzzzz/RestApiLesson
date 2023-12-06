package peaksoft.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.model.User;
import peaksoft.repo.UserRepo;
import peaksoft.security.jwt.JwtService;
import peaksoft.service.AuthenticationService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EntityExistsException(
                    "user with email: " + request.email() + " already exists!"
            );
        }
        User user = User.builder().name(request.firstName())
                .surname(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role()).build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.getUserByEmail(request.email()).orElseThrow(
                () -> new NoSuchElementException(
                        "user with email: " + request.email() + " not fount"));
        if (request.email().isBlank()) {
            throw new BadCredentialsException("email is blank");
        }
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("wrong password");
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
