package ru.computerstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.computerstore.models.Item;
import ru.computerstore.repositories.*;
import ru.computerstore.util.ItemNotFoundException;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final HddRepository hddRepository;
    private final ComputerRepository computerRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, HddRepository hddRepository,
                       ComputerRepository computerRepository, LaptopRepository laptopRepository,
                       MonitorRepository monitorRepository) {
        this.itemRepository = itemRepository;
        this.hddRepository = hddRepository;
        this.computerRepository = computerRepository;
        this.laptopRepository = laptopRepository;
        this.monitorRepository = monitorRepository;
    }

    public Item findById(int id){
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        item.setStockUnits(switch (item.getClass().getSimpleName()){
            case "Hdd" -> hddRepository.count();
            case "Computer" -> computerRepository.count();
            case "Monitor" -> monitorRepository.count();
            case "Laptop" -> laptopRepository.count();
            default -> 0;
        });

        return item;
    }
}
