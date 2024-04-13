package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.models.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByEvent_Id(Long eventId);

    List<Request> findAllByRequester_Id(Long requesterId);

//    @Query("SELECT r FROM Request r " +
//            "WHERE requester.id = ?1 " +
//            "AND event.id = ?2")
    List<Request> findAllByRequesterIdAndEventId(Long requesterId, Long eventId);

}
