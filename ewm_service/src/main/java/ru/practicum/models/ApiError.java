package ru.practicum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;

    private String reason;

    private String message;

    private String timestamp;
}
