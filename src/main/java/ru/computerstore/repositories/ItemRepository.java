package ru.computerstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.computerstore.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
