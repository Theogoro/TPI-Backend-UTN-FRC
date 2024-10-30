package com.backend.empleados.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }
}
