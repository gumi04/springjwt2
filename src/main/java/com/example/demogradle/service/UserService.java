package com.example.demogradle.service;

import com.example.demogradle.model.dto.UserDto;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

  /**
   * Save.
   *
   * @param user the user
   */
  void save(UserDto user);

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  UserDto getById(Integer id);

  /**
   * Update.
   *
   * @param user the user
   */
  void update(UserDto user);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(Integer id);

  /**
   * Gets all.
   *
   * @return the all
   */
  List<UserDto> getAll();


}
