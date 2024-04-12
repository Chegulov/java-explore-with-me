package ru.practicum.controllers.admin_api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.dto.compilation.NewCompilationDto;
import ru.practicum.dto.compilation.UpdateCompilationRequest;
import ru.practicum.services.admin_api.AdminCompilationsService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class AdminCompilationsController {
    private final AdminCompilationsService adminCompilationsService;

    @PostMapping
    public CompilationDto create(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        return adminCompilationsService.create(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    public void delete(@PathVariable Long compId) {
        adminCompilationsService.delete(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto update(@PathVariable Long compId,
                                 @Valid @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return adminCompilationsService.update(compId, updateCompilationRequest);
    }
}
