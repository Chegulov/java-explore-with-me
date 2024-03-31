package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.EndpointHit;
import ru.practicum.model.Stat;

@UtilityClass
public class StatMapper {

    public Stat mapToStat(EndpointHit endpointHit) {
        return Stat.builder()
                .id(endpointHit.getId())
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(endpointHit.getTimestamp())
                .build();
    }

    public EndpointHit mapToHit(Stat stat) {
        return EndpointHit.builder()
                .id(stat.getId())
                .app(stat.getApp())
                .ip(stat.getIp())
                .uri(stat.getUri())
                .timestamp(stat.getTimestamp())
                .build();
    }

}
