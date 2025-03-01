package com.digital_tolk.translations.services;

import com.digital_tolk.translations.dto.request.LoginRequestDTO;
import com.digital_tolk.translations.dto.response.LoginResponseDTO;

public interface AuthService {
  LoginResponseDTO authenticate(LoginRequestDTO request);
}
