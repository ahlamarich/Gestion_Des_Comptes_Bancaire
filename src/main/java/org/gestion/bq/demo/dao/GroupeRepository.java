package org.gestion.bq.demo.dao;

import org.gestion.bq.demo.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
}
