package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
