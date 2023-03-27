package org.gestion.bq.demo.dao;

import org.gestion.bq.demo.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {
}
