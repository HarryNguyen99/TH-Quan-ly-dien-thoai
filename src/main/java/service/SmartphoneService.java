package service;

import model.Smartphone;

public interface SmartphoneService {
    Iterable<Smartphone> findAll();
    Smartphone findById(Long id);
    Smartphone save(Smartphone phone);
    Smartphone remove(Long id);
}
