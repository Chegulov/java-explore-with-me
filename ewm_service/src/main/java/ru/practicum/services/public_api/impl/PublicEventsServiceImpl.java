package ru.practicum.services.public_api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.Constants;
import ru.practicum.EndpointHit;
import ru.practicum.StatsClient;
import ru.practicum.ViewStats;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.exceptions.DataNotFoundException;
import ru.practicum.exceptions.InvalidRequestException;
import ru.practicum.helper.Finder;
import ru.practicum.mappers.EventMapper;
import ru.practicum.models.Event;
import ru.practicum.models.enums.State;
import ru.practicum.repositories.EventRepository;
import ru.practicum.services.public_api.PublicEventsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicEventsServiceImpl implements PublicEventsService {
    private final EventRepository eventRepository;
    private final Finder finder;
    private final StatsClient statsClient;

    @Override
    public List<EventShortDto> getEvents(String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable, HttpServletRequest request, Pageable pageable) {
        if (rangeStart != null && rangeEnd != null && rangeStart.isAfter(rangeEnd)) {
            throw new InvalidRequestException("указа некорректный диапазон дат");
        }

        List<Event> events = eventRepository.getEventsByUserParameters(text, categories, paid, rangeStart, rangeEnd, pageable).getContent();

        if (onlyAvailable) {
            events = events.stream()
                    .filter(event -> event.getConfirmedRequests() < event.getParticipantLimit())
                    .collect(Collectors.toList());
        }

        if (events.isEmpty()) {
            return new ArrayList<>();
        }

        statsClient.create(EndpointHit.builder()
                .app(request.getRemoteAddr())
                .ip(request.getRequestURI())
                .timestamp(LocalDateTime.now().format(Constants.FORMATTER))
                .build());

        return events.stream()
                .map(EventMapper::mapToEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto getEventById(Long id, HttpServletRequest request) {
        Event event = finder.findEventById(id);

        if (!event.getState().equals(State.PUBLISHED)) {
            throw new DataNotFoundException("Событие должно быть опубликовано");
        }

        statsClient.create(EndpointHit.builder()
                .app(request.getRemoteAddr())
                .ip(request.getRequestURI())
                .timestamp(LocalDateTime.now().format(Constants.FORMATTER))
                .build());

        event.setViews(countViews(request));
        eventRepository.save(event);

        return EventMapper.mapToEventFullDto(event);
    }

    private Long countViews(HttpServletRequest request) {
        List<ViewStats> stats = statsClient.readAll(
                LocalDateTime.now().minusYears(300),
                LocalDateTime.now().plusHours(1),
                List.of(request.getRequestURI()),
                true);
        return !stats.isEmpty() ? stats.get(0).getHits() : 0;
    }
}
