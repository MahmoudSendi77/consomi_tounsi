package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Choix;


public interface ChoixRepository  extends JpaRepository<Choix, Long> {

}
