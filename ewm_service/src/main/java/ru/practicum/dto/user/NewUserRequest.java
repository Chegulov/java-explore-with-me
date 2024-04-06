package ru.practicum.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NewUserRequest {
    private String email;
    private String name;
}
