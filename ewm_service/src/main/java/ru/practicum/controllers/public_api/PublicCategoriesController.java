package ru.practicum.controllers.public_api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.services.public_api.PublicCategoriesService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/categories")
public class PublicCategoriesController {
    private final PublicCategoriesService publicCategoriesService;
}
