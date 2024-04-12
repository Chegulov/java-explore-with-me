package ru.practicum.services.admin_api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;
import ru.practicum.exceptions.WrongConditionException;
import ru.practicum.helper.Finder;
import ru.practicum.helper.UtilsUpdateWithoutNull;
import ru.practicum.mappers.EventMapper;
import ru.practicum.models.Event;
import ru.practicum.models.Location;
import ru.practicum.models.enums.AdminStateAction;
import ru.practicum.models.enums.State;
import ru.practicum.repositories.EventRepository;
import ru.practicum.repositories.LocationRepository;
import ru.practicum.services.admin_api.AdminEventsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final Finder finder;

    @Override
    public List<EventFullDto> getEvents(List<Long> initiators, List<String> states, List<Long> categories,
                                        LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable) {
        List<Event> events = eventRepository.getEventsByParameters(initiators, states, categories,
                rangeStart, rangeEnd, pageable).getContent();

        if (events.isEmpty()) {
            return new ArrayList<>();
        }
        return events.stream()
                .map(EventMapper::mapToEventFullDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        Event eventToUpdate = finder.findEventById(eventId);
        LocalDateTime newEventDate = updateEventAdminRequest.getEventDate();

        if (newEventDate != null) {
            if (newEventDate.isBefore(LocalDateTime.now())) {
                throw new WrongConditionException("Дата события не может быть ранее текущего момента");
            }

            if (newEventDate.isBefore(eventToUpdate.getPublishedOn().minusHours(1))) {
                throw new WrongConditionException("Дата начала изменяемого события должна быть не ранее чем за час от даты публикации.");
            }
        }

        if (updateEventAdminRequest.getAdminStateAction() != null) {
            if (!eventToUpdate.getState().equals(State.PENDING)) {
                throw new WrongConditionException("Событие можно публиковать, только если оно в состоянии ожидания публикации. " +
                        "Текущий статус :" + eventToUpdate.getState());
            }

            if (updateEventAdminRequest.getAdminStateAction() == AdminStateAction.PUBLISH_EVENT) {
                eventToUpdate.setState(State.PUBLISHED);
                eventToUpdate.setPublishedOn(LocalDateTime.now());
            } else {
                eventToUpdate.setState(State.CANCELED);
            }
        }

        if (updateEventAdminRequest.getLocation() != null) {
            Location location = locationRepository.save(updateEventAdminRequest.getLocation());
            eventToUpdate.setLocation(location);
        }

        UtilsUpdateWithoutNull.copyProperties(updateEventAdminRequest, eventToUpdate);
        return EventMapper.mapToEventFullDto(eventToUpdate);
    }
}
