package ru.computerstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.computerstore.models.Hdd;
import ru.computerstore.repositories.HddRepository;
import ru.computerstore.util.ItemNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HddService {
    private final HddRepository hddRepository;

    @Autowired
    public HddService(HddRepository hddRepository) {
        this.hddRepository = hddRepository;
    }

    @Transactional
    public void save(Hdd hdd){
        hddRepository.save(hdd);
    }

    @Transactional
    public void update(int id, Hdd hdd){
        hdd.setId(id);
        hddRepository.save(hdd);
    }

    public List<Hdd> findAll(){
        long stockUnits = hddRepository.count();
        List<Hdd> hdds = hddRepository.findAll();
        hdds.forEach(x -> x.setStockUnits(stockUnits));

        return hdds;
    }

    public Hdd findById(int id){
        Hdd hdd = hddRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        hdd.setStockUnits(hddRepository.count());

        return hdd;
    }
}
