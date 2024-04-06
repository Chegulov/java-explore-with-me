package ru.practicum.controllers.admin_api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.services.admin_api.AdminCategoriesService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class AdminCategoriesController {
    private final AdminCategoriesService adminCategoriesService;

    @PostMapping
    public CategoryDto create(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        return adminCategoriesService.create(newCategoryDto);
    }

    @DeleteMapping("{catId}")
    public void delete(@PathVariable Long catId) {
        adminCategoriesService.delete(catId);
    }

    @PatchMapping("{catId}")
    public CategoryDto update(@PathVariable Long catId,
                              @Valid @RequestBody CategoryDto categoryDto) {
        return adminCategoriesService.update(catId, categoryDto);
    }
}
