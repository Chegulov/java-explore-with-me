package ru.practicum.controllers.public_api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.services.public_api.PublicCompilationsService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/compilations")
public class PublicCompilationsController {
    private final PublicCompilationsService publicCompilationsService;
}
