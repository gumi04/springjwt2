package com.example.demogradle.mapper;

import com.example.demogradle.model.dto.UserDto;
import com.example.demogradle.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User mapper.
 */
public final class UserMapper {

  private UserMapper() {
    super();
  }

  /**
   * To entity user.
   *
   * @param dto the dto
   * @return the user
   */
  public static User toEntity(final UserDto dto) {
    User user = new User();
    user.setId(dto.getIdUser());
    user.setUserName(dto.getNombre());
    user.setPassword(dto.getContrasena());
    user.setEmail(dto.getCorreo());
    return user;
  }

  /**
   * To dto user dto.
   *
   * @param entity the entity
   * @return the user dto
   */
  public static UserDto toDto(final User entity) {
    UserDto user = new UserDto();
    user.setIdUser(entity.getId());
    user.setNombre(entity.getUserName());
    user.setContrasena(entity.getPassword());
    user.setCorreo(entity.getEmail());
    return user;
  }


  /**
   * To entity list list.
   *
   * @param dtos the dtos
   * @return the list
   */
  public static List<User> toEntityList(final List<UserDto> dtos) {
    List<User> userList = new ArrayList<>();
    dtos.forEach(dto -> {
      userList.add(toEntity(dto));
    });
    return userList;
  }

  /**
   * To dto list list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<UserDto> toDtoList(final List<User> entities) {
    List<UserDto> userList = new ArrayList<>();
    entities.forEach(entity -> {
      userList.add(toDto(entity));
    });
    return userList;
  }
}
