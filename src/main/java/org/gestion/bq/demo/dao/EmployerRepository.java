package org.gestion.bq.demo.dao;

import org.gestion.bq.demo.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer,Long> {


}
