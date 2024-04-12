package ru.practicum.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.Constants;
import ru.practicum.exceptions.DataNotFoundException;
import ru.practicum.exceptions.InvalidRequestException;
import ru.practicum.exceptions.WrongConditionException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ApiError(HttpStatus.BAD_REQUEST,
                "Incorrectly made request.",
                errors,
                LocalDateTime.now().format(Constants.FORMATTER));
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleDataNotFoundException(DataNotFoundException e) {
        return new ApiError(HttpStatus.NOT_FOUND,
                "The required object was not found.",
                e.getMessage(),
                LocalDateTime.now().format(Constants.FORMATTER));
    }

    @ExceptionHandler(WrongConditionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleWrongConditionException(WrongConditionException e) {
        return new ApiError(HttpStatus.CONFLICT,
                "For the requested operation the conditions are not met",
                e.getMessage(),
                LocalDateTime.now().format(Constants.FORMATTER));
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleInvalidRequestException(InvalidRequestException e) {
        return new ApiError(HttpStatus.BAD_REQUEST,
                "Incorrectly made request",
                e.getMessage(),
                LocalDateTime.now().format(Constants.FORMATTER));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleAnyException(Throwable e) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                e.getMessage(),
                LocalDateTime.now().format(Constants.FORMATTER));
    }
}
