package ru.practicum.services.admin_api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.exceptions.WrongConditionException;
import ru.practicum.helper.Finder;
import ru.practicum.mappers.CategoryMapper;
import ru.practicum.models.Category;
import ru.practicum.repositories.CategoryRepository;
import ru.practicum.repositories.EventRepository;
import ru.practicum.services.admin_api.AdminCategoriesService;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final Finder finder;

    @Override
    public CategoryDto create(NewCategoryDto newCategoryDto) {
        return CategoryMapper.mapToCategoryDto(categoryRepository.save(CategoryMapper.mapToCategory(newCategoryDto)));
    }

    @Override
    public void delete(Long catId) {
        finder.findCategoryById(catId);

        if (!eventRepository.findAllByCategory_Id(catId).isEmpty()) {
            throw new WrongConditionException("Категория не пуста.");
        }

        categoryRepository.deleteById(catId);
    }

    @Override
    public CategoryDto update(Long catId, CategoryDto categoryDto) {
        Category category = finder.findCategoryById(catId);
        return CategoryMapper.mapToCategoryDto(
                categoryRepository.save(CategoryMapper.updateCategory(category, categoryDto)));
    }
}
