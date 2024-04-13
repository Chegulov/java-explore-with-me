package ru.practicum.services.private_api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.exceptions.WrongConditionException;
import ru.practicum.helper.Finder;
import ru.practicum.mappers.RequestMapper;
import ru.practicum.models.Event;
import ru.practicum.models.Request;
import ru.practicum.models.User;
import ru.practicum.models.enums.State;
import ru.practicum.models.enums.Status;
import ru.practicum.repositories.EventRepository;
import ru.practicum.repositories.RequestRepository;
import ru.practicum.services.private_api.PrivateRequestsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivateRequestsServiceImpl implements PrivateRequestsService {
    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final Finder finder;

    @Override
    public List<ParticipationRequestDto> getRequests(Long requesterId) {
        finder.findUserById(requesterId);

        List<Request> requests = requestRepository.findAllByRequester_Id(requesterId);

        if(requests.isEmpty()) {
            return new ArrayList<>();
        }

        return requests.stream()
                .map(RequestMapper::mapToParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationRequestDto create(Long requesterId, Long eventId) {
        if (!requestRepository.findAllByRequesterIdAndEventId(requesterId, eventId).isEmpty()) {
            throw new WrongConditionException("нельзя добавить повторный запрос");
        }

        Event event = finder.findEventById(eventId);

        if (!event.getState().equals(State.PUBLISHED)) {
            throw new WrongConditionException("нельзя участвовать в неопубликованном событии");
        }

        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new WrongConditionException("у события достигнут лимит запросов на участие");
        }

        if (requesterId.equals(event.getInitiator().getId())) {
            throw new WrongConditionException("инициатор события не может добавить запрос на участие в своём событии");
        }
        User requester = finder.findUserById(requesterId);

        Request request = Request.builder()
                .created(LocalDateTime.now())
                .requester(requester)
                .event(event)
                .status(Status.PENDING)
                .build();

        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            request.setStatus(Status.CONFIRMED);
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            eventRepository.save(event);
        }

        return RequestMapper.mapToParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long requesterId, Long requestId) {
        Request request = finder.findRequestById(requestId);

        request.setStatus(Status.CANCELED);
        return RequestMapper.mapToParticipationRequestDto(requestRepository.save(request));
    }
}