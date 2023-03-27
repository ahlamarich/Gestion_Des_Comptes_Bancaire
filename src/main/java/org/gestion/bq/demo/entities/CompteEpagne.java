package org.gestion.bq.demo.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("CE")
public class CompteEpagne extends Compte {
    private double taux;

    public CompteEpagne() {
    }

    public CompteEpagne(String codeCt, Date DTcreation, Double solde, Client client, double taux) {
        super(codeCt, DTcreation, solde, client);
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
