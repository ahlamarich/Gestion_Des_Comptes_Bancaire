package org.gestion.bq.demo.dao;

import org.gestion.bq.demo.entities.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM Client c WHERE c.nomCL LIKE :x")
    public Page<Client> ConsulterClient(@Param("x") String keyword,Pageable pageable);




    @Query("SELECT COUNT(c) FROM Client c")
    public Integer getALLClient();


}
