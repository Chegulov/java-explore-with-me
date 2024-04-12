package ru.practicum.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.Constants;
import ru.practicum.models.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class NewEventDto {
    @Size(min = 20, max = 2000)
    @NotBlank(message = "аннотация не может быть пустой")
    private String annotation;
    private Long category;
    @Size(min = 20, max = 7000)
    @NotBlank(message = "описание не может быть пустым")
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.pattern)
    private LocalDateTime  eventDate;
    private Location location;
    private Boolean paid = false;
    private  Long participantLimit = 0L;
    private Boolean requestModeration = true;
    @Size(min = 3, max = 120)
    @NotBlank(message = "заголовок не может быть пустым")
    private String title;
}
