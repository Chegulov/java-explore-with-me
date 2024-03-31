package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.EndpointHit;
import ru.practicum.model.Stat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class StatMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Stat mapToStat(EndpointHit endpointHit) {
        return Stat.builder()
                .id(endpointHit.getId())
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(LocalDateTime.parse(endpointHit.getTimestamp(), formatter))
                .build();
    }

    public EndpointHit mapToHit(Stat stat) {
        return EndpointHit.builder()
                .id(stat.getId())
                .app(stat.getApp())
                .ip(stat.getIp())
                .uri(stat.getUri())
                .timestamp(stat.getTimestamp().toString())
                .build();
    }

}
