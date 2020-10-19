package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Delivery;



@Repository
public interface DeliveyRepository extends JpaRepository<Delivery,Long>{

}
