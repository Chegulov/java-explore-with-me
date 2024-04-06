package ru.practicum.controllers.public_api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.services.public_api.PublicEventsService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/events")
public class PublicEventsController {
    private final PublicEventsService publicEventsService;
}
