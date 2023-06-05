package ru.computerstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.computerstore.models.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {
}
