package ru.practicum.controllers.public_api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.Constants;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.exceptions.InvalidRequestException;
import ru.practicum.services.public_api.PublicEventsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/events")
public class PublicEventsController {
    private final PublicEventsService publicEventsService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(required = false) String text,
                                         @RequestParam(required = false) List<Long> categories,
                                         @RequestParam(required = false) Boolean paid,
                                         @RequestParam(required = false)
                                             @DateTimeFormat(pattern = Constants.pattern) LocalDateTime rangeStart,
                                         @RequestParam(required = false)
                                             @DateTimeFormat(pattern = Constants.pattern)
                                             LocalDateTime rangeEnd,
                                         @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam(defaultValue = "0") Integer from,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         HttpServletRequest request) {
        Pageable pageable = PageRequest.of(from / size, size);

        if (sort != null) {
            if (sort.equals("EVENT_DATE")) {
                pageable = PageRequest.of(from / size, size, Sort.by("eventDate"));
            } else if (sort.equals("VIEWS")) {
                pageable = PageRequest.of(from / size, size, Sort.by("views"));
            } else {
                throw new InvalidRequestException("Параметр sort задан неверно");
            }
        }

        return publicEventsService.getEvents(text, categories, paid, rangeStart,
                rangeEnd, onlyAvailable, request, pageable);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable Long id, HttpServletRequest request) {
        return publicEventsService.getEventById(id, request);
    }
}
