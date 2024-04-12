package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.models.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByEvent_Id(Long eventId);

    List<Request> findAllByRequester_Id(Long requesterId);

    @Query("SELECT er FROM EventRequest er " +
            "WHERE er.requester.id = ?1 " +
            "AND er.event.id = ?2")
    List<Object> findUserEventRequestByEvent(Long requesterId, Long eventId);
}
