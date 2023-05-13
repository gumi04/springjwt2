package com.example.demogradle.service;

import com.example.demogradle.exception.ResourceNotFoundException;
import com.example.demogradle.mapper.UserMapper;
import com.example.demogradle.model.dto.UserDto;
import com.example.demogradle.model.entity.User;
import com.example.demogradle.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
@Log4j
public class UserServiceImpl implements UserService {
  private UserRepository repository;


  public UserServiceImpl(UserRepository userRepository){
    this.repository = userRepository;
  }




  /**
   * Save.
   *
   * @param user the user
   */
  @Override
  public void save(UserDto user) {
    User entity = UserMapper.toEntity(user);
    user.setContrasena(new BCryptPasswordEncoder().encode(user.getContrasena()));
    repository.save(entity);
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @Override
  public UserDto getById(Integer id) {
    Optional<User> optionalUser = repository.findById(id);
    User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    return UserMapper.toDto(user);
  }

  /**
   * Update.
   *
   * @param user the user
   */
  @Override
  public void update(UserDto user) {
    save(user);
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @Override
  public void delete(Integer id) {
    Optional<User> optionalUser = repository.findById(id);
    User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    repository.delete(user);
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  @Override
  public List<UserDto> getAll() {
    return repository.
        findAll().
        stream().
        map(user -> UserMapper.toDto(user))
        .collect(Collectors.toList());
  }
}
