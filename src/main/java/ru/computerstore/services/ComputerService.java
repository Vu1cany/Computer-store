package ru.computerstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.computerstore.models.Computer;
import ru.computerstore.repositories.ComputerRepository;
import ru.computerstore.util.ItemNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComputerService{
    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> findAll(){
        long stockUnits = computerRepository.count();
        List<Computer> computers = computerRepository.findAll();
        computers.forEach(x -> x.setStockUnits(stockUnits));
        return computers;
    }

    public Computer findById(int id){
        Computer computer = computerRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        computer.setStockUnits(computerRepository.count());

        return computer;
    }

    @Transactional
    public void save(Computer computer){
        computerRepository.save(computer);
    }

    @Transactional
    public void update(int id, Computer computer){
        computer.setId(id);
        computerRepository.save(computer);
    }

}
