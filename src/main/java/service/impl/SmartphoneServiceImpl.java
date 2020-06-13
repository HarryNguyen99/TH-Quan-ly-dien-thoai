package service.impl;

import model.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import repository.SmartphoneRepository;
import service.SmartphoneService;

public class SmartphoneServiceImpl implements SmartphoneService {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Override
    public Iterable<Smartphone> findAll() {
        return smartphoneRepository.findAll();
    }

    @Override
    public Smartphone findById(Long id) {
      Smartphone smartphone =  smartphoneRepository.findOne(id);
      if (smartphone == null){
          return null;
      }
      return smartphone;
    }

    @Override
    public Smartphone save(Smartphone phone) {
        return smartphoneRepository.save(phone);
    }

    @Override
    public Smartphone remove(Long id) {
        Smartphone smartphone = findById(id);
        smartphoneRepository.delete(id);
        return smartphone;
    }
}
