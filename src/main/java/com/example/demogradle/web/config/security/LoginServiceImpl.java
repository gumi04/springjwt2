package com.example.demogradle.web.config.security;

import com.example.demogradle.exception.ResourceNotFoundException;
import com.example.demogradle.model.entity.User;
import com.example.demogradle.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The type Login service.
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  /**
   * Instantiates a new Login service.
   *
   * @param userRepository the user repository
   */
  public LoginServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  /**
   * Locates the user based on the username. In the actual implementation, the search
   * may possibly be case sensitive, or case insensitive depending on how the
   * implementation instance is configured. In this case, the <code>UserDetails</code>
   * object that comes back may have a username that is of a different case than what
   * was actually requested..
   *
   * @param email el correo del usuario
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   *                                   GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository
        .findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario " + email + " no encontrado"));
    return new UserDetailsImpl(user);
  }
}
