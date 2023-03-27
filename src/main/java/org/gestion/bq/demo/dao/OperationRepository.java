package org.gestion.bq.demo.dao;

import org.gestion.bq.demo.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation,Long> {
    @Query("SELECT o FROM Operation o WHERE o.compte.codeCt=:x order by o.dateOperation DESC")
    public Page<Operation> ListOperation(@Param("x") String codeCt,Pageable pageable);

    @Query("SELECT COUNT(o) FROM Operation o WHERE o.class LIKE 'R' ")
    public Integer getNumRetrait();

    @Query("SELECT COUNT(o) FROM Operation o WHERE o.class LIKE 'V' ")
    public Integer getNumDepot();

    @Query("SELECT o FROM Operation o WHERE  DATE(o.dateOperation) = DATE(NOW())")
    public Page<Operation> ListOperationJour(Pageable pageable);
}
