package org.gestion.bq.demo.dao;


import org.gestion.bq.demo.entities.Client;
import org.gestion.bq.demo.entities.Compte;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;


public interface CompteRepository extends JpaRepository<Compte,String> {


    @Query("SELECT c FROM Compte c WHERE c.codeCt LIKE :x")
    public Page<Compte> ConsulterCompte1(@Param("x") String codeCt,Pageable pageable);

    @Query("SELECT c FROM Compte c WHERE c.codeCt=:x order by c.DTcreation DESC")
    public Page<Compte> ListComptes(@Param("x") String codeCt, Pageable pageable);


    @Query("SELECT COUNT(c) FROM Compte c")
    public Integer getALLCompte();


    @Modifying
    @Query(
            value =
                    "insert into compte (codeCt, DTcreation, solde, nomCL,decouvert) values (:codeCt, :DTcreation, :solde, :nomCL,:decouvert)",
            nativeQuery = true)
    void AddCompteCourant(@Param("codeCt") String codeCt, @Param("DTcreation") Date DTcreation,
                    @Param("solde") double solde, @Param("nomCL") String nomCL,@Param("decouvert") double decouvert);

    @Modifying
    @Query(
            value =
                    "insert into compte (codeCt, DTcreation, solde, nomCL,taux) values (:codeCt, :DTcreation, :solde, :nomCL,:taux)",
            nativeQuery = true)
    void AAddCompteEpagne(@Param("codeCt") String codeCt, @Param("DTcreation") Date DTcreation,
                          @Param("solde") double solde, @Param("nomCL") String nomCL, @Param("taux") double taux);

}
