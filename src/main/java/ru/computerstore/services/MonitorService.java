package ru.computerstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.computerstore.models.Monitor;
import ru.computerstore.repositories.MonitorRepository;
import ru.computerstore.util.ItemNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MonitorService {
    private final MonitorRepository monitorRepository;

    @Autowired
    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List<Monitor> findAll(){
        long stockUnits = monitorRepository.count();
        List<Monitor> monitors = monitorRepository.findAll();
        monitors.forEach(x -> x.setStockUnits(stockUnits));

        return monitors;
    }

    public Monitor findById(int id){
        Monitor monitor = monitorRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        monitor.setStockUnits(monitorRepository.count());

        return monitor;
    }

    @Transactional
    public void save(Monitor monitor){
        monitorRepository.save(monitor);
    }

    @Transactional
    public void update(int id, Monitor monitor){
        monitor.setId(id);
        monitorRepository.save(monitor);
    }
}
