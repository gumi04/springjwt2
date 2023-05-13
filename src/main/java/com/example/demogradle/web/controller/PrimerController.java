package com.example.demogradle.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.valueOf;

@RestController
@RequestMapping("/hola")
@Slf4j
@CrossOrigin
public class PrimerController {


  @ApiOperation(value = "Publica un mensaje en la cola normal de Rabbit MD", response = String.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully process", response = String.class),
      @ApiResponse(code = 204, message = "Ocurrió un error", response = String.class),
      @ApiResponse(code = 404, message = "No se encontró información", response = String.class)})
  @GetMapping(value = "/saludo", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity<String> saluda(@RequestParam String nombre){
    Integer status = HttpStatus.OK.value();
    String saludo = null;
    try {
      saludo = "Hola mundo " + nombre;
    } catch (Exception ex) {
      log.info("Error al obtener publicar mensaje...");
      log.error(ex.getMessage(), ex);
      status = HttpStatus.INTERNAL_SERVER_ERROR.value();
      saludo = ex.getMessage();
    }
    return new ResponseEntity<>(saludo, valueOf(status));

  }
}
