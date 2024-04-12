package ru.practicum.services.admin_api;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminEventsService {
    List<EventFullDto> getEvents(List<Long> initiators, List<String> states, List<Long> categories,
                                 LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
