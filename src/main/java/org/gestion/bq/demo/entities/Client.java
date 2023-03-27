package org.gestion.bq.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
@Entity
@Table(name = "Clients",uniqueConstraints = @UniqueConstraint(columnNames = "Cin"))
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CODE_CLI")
    private Long codeCL;
    @NotBlank(message = "Entez Votre nom")
    private String nomCL;
    @NotBlank(message = "Entez Votre email")
    @Email(message = "Entrez une adresse Email validée")
    private String adresseCL;
    @NotBlank(message = "Entez Votre Cin")

    private String Cin;
    @NotBlank(message = "Entez Votre numéro de téléphone")

    private String Tel;

    //RELATION BIDIMENTIONNEL
    @OneToMany(mappedBy = "client",fetch =FetchType.LAZY)
    private Collection<Compte> comptes;


    public Client() {
    }

    public Client(String nomCL, String adresseCL) {
        this.nomCL = nomCL;
        this.adresseCL = adresseCL;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public Long getCodeCL() {
        return codeCL;
    }

    public void setCodeCL(Long codeCL) {
        this.codeCL = codeCL;
    }

    public String getNomCL() {
        return nomCL;
    }

    public void setNomCL(String nomCL) {
        this.nomCL = nomCL;
    }

    public String getAdresseCL() {
        return adresseCL;
    }

    public void setAdresseCL(String adresseCL) {
        this.adresseCL = adresseCL;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }
}
