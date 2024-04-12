package ru.practicum.services.public_api;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface PublicEventsService {
    List<EventShortDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd, Boolean onlyAvailable, HttpServletRequest request, Pageable pageable);

    EventFullDto getEventById(Long id, HttpServletRequest request);
}
