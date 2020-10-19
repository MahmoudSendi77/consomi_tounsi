package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Reporting;

public interface ReportingRepository extends JpaRepository<Reporting, Long> {

}
