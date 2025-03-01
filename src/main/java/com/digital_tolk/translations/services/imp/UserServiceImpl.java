package com.digital_tolk.translations.services.imp;

import com.digital_tolk.translations.Entities.User;
import com.digital_tolk.translations.Repositories.UserRepository;
import com.digital_tolk.translations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
