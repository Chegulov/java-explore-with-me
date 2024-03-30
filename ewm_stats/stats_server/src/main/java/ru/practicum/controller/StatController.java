package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.EndpointHit;
import ru.practicum.ViewStats;
import ru.practicum.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StatController {
    private final StatService statService;

    @PostMapping("/hit")
    public EndpointHit create(@RequestBody EndpointHit endpointHit) {
        return statService.create(endpointHit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        return statService.getStats(start, end, uris, unique);
    }
}
