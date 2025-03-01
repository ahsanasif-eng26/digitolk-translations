package com.digital_tolk.translations.controllers;

import com.digital_tolk.translations.dto.request.LoginRequestDTO;
import com.digital_tolk.translations.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  @Autowired
  private AuthService authService;


  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
    return ResponseEntity.ok(ResponseWrapper
        .builder()
        .status(Boolean.TRUE)
        .data(authService.authenticate(request))
        .build());
  }
}
