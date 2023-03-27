package org.gestion.bq.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{

    public Retrait() {
    }

    public Retrait(Date dateOperation, Double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }
}
