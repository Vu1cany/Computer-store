package ru.computerstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.computerstore.models.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
}
