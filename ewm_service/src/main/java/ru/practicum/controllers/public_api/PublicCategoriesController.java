package ru.practicum.controllers.public_api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.services.public_api.PublicCategoriesService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/categories")
public class PublicCategoriesController {
    private final PublicCategoriesService publicCategoriesService;

    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);

        return publicCategoriesService.getCategories(pageable);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        return publicCategoriesService.getCategoryById(catId);
    }
}
