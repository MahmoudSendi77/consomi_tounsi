package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.newsletter;



public interface NewsletterRepository extends JpaRepository <newsletter,Long> {

}
