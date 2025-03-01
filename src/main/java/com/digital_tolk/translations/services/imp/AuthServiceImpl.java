package com.digital_tolk.translations.services.imp;

import com.digital_tolk.translations.Entities.User;
import com.digital_tolk.translations.dto.request.LoginRequestDTO;
import com.digital_tolk.translations.dto.response.LoginResponseDTO;
import com.digital_tolk.translations.services.AuthService;
import com.digital_tolk.translations.services.UserService;
import com.digital_tolk.translations.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtUtil jwtUtil;

  @Override
  public LoginResponseDTO authenticate(LoginRequestDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    UserDetails userDetails = (User) userService.findByUsername(request.getUsername())
        .orElseThrow(() -> new RuntimeException("User not found"));
    Map<String, Object> claims = new HashMap<>();
    User user = userService.findByUsername(request.getUsername()).get();
    claims.put("role", user.getRole());

    String token = jwtUtil.generateToken(user.getUsername(), claims);
    return new LoginResponseDTO(token);
  }

}
