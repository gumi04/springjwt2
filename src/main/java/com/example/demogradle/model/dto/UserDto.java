package com.example.demogradle.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class UserDto implements Serializable {

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private static final long serialVersionUID = 2l;

  private Integer idUser;
  private String nombre;
  private String contrasena;
  private String correo;
}
