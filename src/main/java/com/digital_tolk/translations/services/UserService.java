package com.digital_tolk.translations.services;


import com.digital_tolk.translations.Entities.User;

import java.util.Optional;

public interface UserService {
  Optional<User> findByUsername(String username);
}
