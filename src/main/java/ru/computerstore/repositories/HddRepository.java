package ru.computerstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.computerstore.models.Hdd;

@Repository
public interface HddRepository extends JpaRepository<Hdd, Integer> {

}
