package com.backend.empleados.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GenericException extends RuntimeException {
  public GenericException(String message) {
    super(message);
  }
  
}
