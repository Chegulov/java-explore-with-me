package ru.practicum.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.Constants;
import ru.practicum.models.enums.Status;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ParticipationRequestDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.pattern)
    private LocalDateTime created;
    private Long event;
    private Long requester;
    private Status status;
}
