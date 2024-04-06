package ru.practicum.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.user.UserShortDto;
import ru.practicum.models.Location;
import ru.practicum.models.enums.State;

@Data
@Builder
@AllArgsConstructor
public class EventFullDto {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private Long confirmedRequests;
    private String eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
    private String createdOn;
    private String description;
    public int participantLimit;
    private String publishedOn;
    private Boolean requestModeration;
    private State state;
    private Location location;
}
