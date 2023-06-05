package ru.computerstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.computerstore.models.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Integer> {
}
