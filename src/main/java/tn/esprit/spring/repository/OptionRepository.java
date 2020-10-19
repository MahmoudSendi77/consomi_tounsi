package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Choix;

public interface OptionRepository extends CrudRepository<Choix, Long> {
    
    void deleteByPollId(Long id);
    
}
