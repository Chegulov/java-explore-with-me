package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
