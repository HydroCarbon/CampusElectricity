package io.hydrocarbon.campus.electricity.service.authentication;

import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import io.hydrocarbon.campus.electricity.enums.Role;
import io.hydrocarbon.campus.electricity.param.request.LoginRequest;
import io.hydrocarbon.campus.electricity.param.request.RegisterRequest;
import io.hydrocarbon.campus.electricity.param.response.authentication.AuthenticationResponse;
import io.hydrocarbon.campus.electricity.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPhone(request.getPhone());
        userEntity.setRoomId(request.getRoomId());
        userEntity.setStudentNo(request.getStudentNo());
        userEntity.setRole(Role.USER.name());

        userRepository.save(userEntity);
        var jwt = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
