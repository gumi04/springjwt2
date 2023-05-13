package com.example.demogradle.web.controller;

import com.example.demogradle.model.dto.ResponseDefault;
import com.example.demogradle.model.dto.UserDto;
import com.example.demogradle.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.valueOf;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

  private UserService userService;


  public UserController(UserService userService){
      this.userService = userService;
  }

  @ApiOperation(value = "Guarda Usuario", response = ResponseDefault.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully process", response = ResponseDefault.class),
      @ApiResponse(code = 204, message = "Ocurrió un error", response = ResponseDefault.class),
      @ApiResponse(code = 404, message = "No se encontró información", response = ResponseDefault.class)})
  @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<ResponseDefault> saveUser(@RequestBody UserDto userDto){
    Integer status = HttpStatus.CREATED.value();
    ResponseDefault responseDefault = null;
    try {
      userService.save(userDto);
      responseDefault = new ResponseDefault(status, "Usuario creado", null);
    } catch (Exception ex) {
      log.info("Error al obtener publicar mensaje...");
      log.error(ex.getMessage(), ex);
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      responseDefault = new ResponseDefault(status, ex.getMessage(), null);
    }
    return new ResponseEntity<>(responseDefault, valueOf(status));
  }

  @ApiOperation(value = "Actualiza Usuario", response = ResponseDefault.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully process", response = ResponseDefault.class),
      @ApiResponse(code = 204, message = "Ocurrió un error", response = ResponseDefault.class),
      @ApiResponse(code = 404, message = "No se encontró información", response = ResponseDefault.class)})
  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<ResponseDefault> updateUser(@RequestBody UserDto userDto){
    Integer status = HttpStatus.OK.value();
    ResponseDefault responseDefault = null;
    try {
      userService.update(userDto);
      responseDefault = new ResponseDefault(status, "Usuario actualizado", null);
    } catch (Exception ex) {
      log.info("Error al obtener publicar mensaje...");
      log.error(ex.getMessage(), ex);
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      responseDefault = new ResponseDefault(status, ex.getMessage(), null);
    }
    return new ResponseEntity<>(responseDefault, valueOf(status));
  }

  @ApiOperation(value = "Obtiene Usuario", response = ResponseDefault.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully process", response = ResponseDefault.class),
      @ApiResponse(code = 204, message = "Ocurrió un error", response = ResponseDefault.class),
      @ApiResponse(code = 404, message = "No se encontró información", response = ResponseDefault.class)})
  @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<ResponseDefault> saveUser(@PathVariable Integer id){
    Integer status = HttpStatus.OK.value();
    ResponseDefault responseDefault = null;
    try {
      responseDefault = new ResponseDefault(status, "Usuario encontrado", userService.getById(id));
    } catch (Exception ex) {
      log.info("Error al obtener publicar mensaje...");
      log.error(ex.getMessage(), ex);
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      responseDefault = new ResponseDefault(status, ex.getMessage(), null);
    }
    return new ResponseEntity<>(responseDefault, valueOf(status));
  }

  @ApiOperation(value = "Elimina Usuario", response = ResponseDefault.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully process", response = ResponseDefault.class),
      @ApiResponse(code = 204, message = "Ocurrió un error", response = ResponseDefault.class),
      @ApiResponse(code = 404, message = "No se encontró información", response = ResponseDefault.class)})
  @PutMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<ResponseDefault> deleteUser(@PathVariable Integer id){
    Integer status = HttpStatus.ACCEPTED.value();
    ResponseDefault responseDefault = null;
    try {
      responseDefault = new ResponseDefault(status, "Usuario eliminado", userService.getById(id));
    } catch (Exception ex) {
      log.info("Error al obtener publicar mensaje...");
      log.error(ex.getMessage(), ex);
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      responseDefault = new ResponseDefault(status, ex.getMessage(), null);
    }
    return new ResponseEntity<>(responseDefault, valueOf(status));
  }

  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully process", response = ResponseDefault.class),
      @ApiResponse(code = 204, message = "Ocurrió un error", response = ResponseDefault.class),
      @ApiResponse(code = 404, message = "No se encontró información", response = ResponseDefault.class)})
  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<ResponseDefault> getAllUser(){
    Integer status = HttpStatus.OK.value();
    ResponseDefault responseDefault = null;
    try {
      responseDefault = new ResponseDefault(status, "Usuario obtenidos", userService.getAll());
    } catch (Exception ex) {
      log.info("Error al obtener publicar mensaje...");
      log.error(ex.getMessage(), ex);
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      responseDefault = new ResponseDefault(status, ex.getMessage(), null);
    }
    return new ResponseEntity<>(responseDefault, valueOf(status));
  }


}
