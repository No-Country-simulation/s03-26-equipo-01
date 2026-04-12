package com.cms.controller.exception;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException; // <-- Verifica que este import coincida con tu superclase
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFound(
            EntityNotFoundException exception,
            HttpServletRequest request
    ) {
        return ErrorResponseDTO.buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler({BusinessException.class, UnsupportedOperationException.class})
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(
            BusinessException exception,
            HttpServletRequest request
    ) {
        return ErrorResponseDTO.buildResponse(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class })
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(
            Exception exception, HttpServletRequest request) {
        return ErrorResponseDTO.buildResponse(HttpStatus.FORBIDDEN,
                "No tiene permisos para realizar esta acción", request);
    }



    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(
            Exception exception,
            HttpServletRequest request
    ) {
        String message = exception.getMessage();
        if (exception instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            message = methodArgumentNotValidException.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(FieldError::getField)
                    .distinct()
                    .collect(Collectors.joining(", "));
        }

        return ErrorResponseDTO.buildResponse(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(
            BadCredentialsException exception,
            HttpServletRequest request
    ) {
        return ErrorResponseDTO.buildResponse(HttpStatus.UNAUTHORIZED, "Credenciales inválidas", request);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleExpiredJwt(
            ExpiredJwtException exception,
            HttpServletRequest request
    ) {
        return ErrorResponseDTO.buildResponse(HttpStatus.UNAUTHORIZED, "Token expirado", request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exception,HttpServletRequest request) {

        ErrorResponseDTO error = ErrorResponseDTO.of(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                "Ha ocurrido un error inesperado. Por favor, contacte al administrador del sistema.",
                request.getRequestURI()
        );

        log.error(request.getRequestURI(), error);
        log.error(String.valueOf(exception));

        return ResponseEntity.internalServerError().body(error);
    }
}