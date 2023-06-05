package ru.computerstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.computerstore.models.Laptop;
import ru.computerstore.repositories.LaptopRepository;
import ru.computerstore.util.ItemNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LaptopService {
    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Transactional
    public void save(Laptop laptop){
        laptopRepository.save(laptop);
    }

    @Transactional
    public void update(int id, Laptop laptop){
        laptop.setId(id);
        laptopRepository.save(laptop);
    }

    public List<Laptop> findAll(){
        long stockUnits = laptopRepository.count();
        List<Laptop> laptops = laptopRepository.findAll();
        laptops.forEach(x -> x.setStockUnits(stockUnits));

        return laptops;
    }

    public Laptop findById(int id){
        Laptop laptop = laptopRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        laptop.setStockUnits(laptopRepository.count());

        return laptop;
    }
}
