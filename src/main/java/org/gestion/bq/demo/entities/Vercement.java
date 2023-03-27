package org.gestion.bq.demo.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
@Entity
@DiscriminatorValue("V")
public class Vercement extends Operation {

    public Vercement() {
    }

    public Vercement(Date dateOperation, Double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }
}
