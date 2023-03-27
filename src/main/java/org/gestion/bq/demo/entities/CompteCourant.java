package org.gestion.bq.demo.entities;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant() {
    }



    public CompteCourant(String codeCt, Date DTcreation, Double solde, Client client, double decouvert) {
        super(codeCt, DTcreation, solde, client);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
