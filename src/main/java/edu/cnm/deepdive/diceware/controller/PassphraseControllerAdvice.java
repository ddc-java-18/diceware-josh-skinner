package edu.cnm.deepdive.diceware.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PassphraseControllerAdvice {

  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Validation failed")
  public void validationFailure() {

  }

}
