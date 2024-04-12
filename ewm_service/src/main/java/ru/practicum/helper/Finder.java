package ru.practicum.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.exceptions.DataNotFoundException;
import ru.practicum.models.*;
import ru.practicum.repositories.*;

@Component
@RequiredArgsConstructor
public class Finder {
    private final CategoryRepository categoryRepository;
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public Category findCategoryById(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new DataNotFoundException("Категория с id=" + catId + " не найдена."));
    }

    public Compilation findCompilationById(Long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new DataNotFoundException("Компиляция с id=" + compId + " не найдена."));
    }

    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new DataNotFoundException("Событие с id=" + eventId + " не найдено."));
    }

    public Request findRequestById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new DataNotFoundException("Запрос с id=" + requestId + " не найден."));
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Пользователь с id=" + userId + " не найден."));
    }
}
