package org.gestion.bq.demo.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",discriminatorType = DiscriminatorType.STRING,length = 4)
public class Compte implements Serializable {
    @Id
    @Column(name="Code")
    private String codeCt;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date DTcreation;
    private double solde;
    @ManyToOne
    @JoinColumn(name = "CODE_CLI")
    private  Client client;
    @ManyToOne
    @JoinColumn(name="codeEmp")
    private Employer employer;
    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;


    public Compte() {
    }



    public Compte(String codeCt, Date DTcreation, Double solde, Client client) {
        this.codeCt = codeCt;
        this.DTcreation = DTcreation;
        this.solde = solde;
        this.client = client;
    }


    public String getCodeCt() {
        return codeCt;
    }

    public void setCodeCt(String codeCt) {
        this.codeCt = codeCt;
    }

    public Date getDTcreation() {
        return DTcreation;
    }

    public void setDTcreation(Date DTcreation) {
        this.DTcreation = DTcreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }
}
