package org.gestion.bq.demo.dao;

import org.gestion.bq.demo.entities.Client;
import org.gestion.bq.demo.entities.Utilisateurs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends JpaRepository<Utilisateurs,Long> {
    @Query("SELECT u FROM Utilisateurs u WHERE u.username LIKE :x")
    public Page<Utilisateurs> Consulterusers(@Param("x") String keyword, Pageable pageable);

}
