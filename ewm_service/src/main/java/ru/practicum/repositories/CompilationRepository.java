package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.models.Compilation;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
