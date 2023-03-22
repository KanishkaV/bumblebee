package com.bumble.bee.app.rest.controller;

import com.bumble.bee.app.exceptions.EntityNotFoundException;
import com.bumble.bee.app.exceptions.WrongParameterException;
import com.bumble.bee.app.models.error.BadRequestErrorDto;
import com.bumble.bee.app.models.error.DefaultErrorDto;
import com.bumble.bee.app.models.error.ErrorCodes;
import com.bumble.bee.app.models.error.ParseRequestErrorDto;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class) // 404
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.warn("Handled error EntityNotFoundException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        return this.handleExceptionInternal(ex, DefaultErrorDto.of(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IllegalArgumentException.class) // 400
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.warn("Handled error IllegalArgumentException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        return this.handleExceptionInternal(ex, DefaultErrorDto.of(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class) // 400
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.warn("Handled error ConstraintViolationException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        return this.handleExceptionInternal(ex, BadRequestErrorDto.of(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ValidationException.class) // 400
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        log.warn("Handled error ValidationException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        return this.handleExceptionInternal(ex, BadRequestErrorDto.of(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleThrowable(Exception ex, WebRequest request) {
        log.error("Handled error '{}' with message: {}", ex.getClass(), ex.getMessage());
        log.error("Handled error: ", ex);
        return this.handleExceptionInternal(ex, DefaultErrorDto.of(ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(WrongParameterException.class)
    public ResponseEntity<Object> handleWrongParameterException(WrongParameterException ex, WebRequest request) {
        log.warn("Handled error WrongParameterException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        return this.handleExceptionInternal(ex, ParseRequestErrorDto.of(ex.getMessage()),
                                            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {
        super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
        log.warn("Handled error HttpRequestMethodNotSupportedException: {}", ex.getMessage());
        log.debug("Details: ", ex);

        return this.handleExceptionInternal(ex, DefaultErrorDto.of(ErrorCodes.METHOD_NOT_ALLOWED, ex.getMessage()),
                                            new HttpHeaders(),
                                            HttpStatus.METHOD_NOT_ALLOWED,
                                            request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        ParseRequestErrorDto parseRequestErrorDto = null;
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasGlobalErrors()) {
            parseRequestErrorDto = ParseRequestErrorDto.withMessage(Objects.requireNonNull(bindingResult.getGlobalError()).getDefaultMessage());
        } else if (bindingResult.hasFieldErrors()) {
            parseRequestErrorDto = ParseRequestErrorDto.withMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        log.error("Handled error MethodArgumentNotValidException: {}", ex.getMessage());
        log.debug("Details: ", ex);

        return this.handleExceptionInternal(ex, parseRequestErrorDto,
                                            new HttpHeaders(),
                                            HttpStatus.BAD_REQUEST,
                                            request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          WebRequest request) {
        log.error("Handled error MissingServletRequestParameterException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        return this.handleExceptionInternal(ex, ParseRequestErrorDto.withMessage(ex.getMessage()),
                                            new HttpHeaders(),
                                            HttpStatus.BAD_REQUEST,
                                            request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatusCode status,
                                                                      WebRequest request) {
        log.error("Handled error HttpMediaTypeNotAcceptable: {}", ex.getMessage());
        log.debug("Details: ", ex);
        String supportedTypes = ex.getSupportedMediaTypes().stream().map(MimeType::toString)
                                  .collect(Collectors.joining(", "));
        return this.handleExceptionInternal(ex, DefaultErrorDto.builder()
                                                               .code(ErrorCodes.WRONG_CONTENT_TYPE)
                                                               .description("Media type is not acceptable. Supported types are : %s".formatted(supportedTypes))
                                                               .build(),
                                            new HttpHeaders(),
                                            HttpStatus.PRECONDITION_FAILED,
                                            request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error("Handled error HttpMessageNotReadableException: {}", ex.getMessage());
        log.debug("Details: ", ex);
        ParseRequestErrorDto parseRequestErrorDto = ParseRequestErrorDto.notReadableRequest();
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof JsonParseException) {
            parseRequestErrorDto = this.handleJsonParseError((JsonParseException) cause);
        } else if (cause instanceof InvalidFormatException) {
            String propertyName = ((InvalidFormatException) cause).getPath().stream()
                                                                  .map(JsonMappingException.Reference::getFieldName)
                                                                  .filter(Objects::nonNull)
                                                                  .collect(Collectors.joining("."));
            parseRequestErrorDto = ParseRequestErrorDto.withParameter(propertyName);
        }
        return this.handleExceptionInternal(ex, parseRequestErrorDto,
                                            new HttpHeaders(),
                                            HttpStatus.BAD_REQUEST,
                                            request);
    }

    private ParseRequestErrorDto handleJsonParseError(JsonParseException e) {
        String errorMessage = this.formatJsonLocation(e.getLocation()) + e.getOriginalMessage();
        return ParseRequestErrorDto.withMessage(errorMessage);
    }

    private String formatJsonLocation(JsonLocation location) {
        return location != null ? String.format("line %d, col %d: ", location.getLineNr(), location.getColumnNr()) : "";
    }
}
