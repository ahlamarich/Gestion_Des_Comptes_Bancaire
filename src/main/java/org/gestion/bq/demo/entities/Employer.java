package org.gestion.bq.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEmp;
    private String nomEmp;
    @ManyToOne
    @JoinColumn(name = "CODE_EMP_SUP")
    private Employer employerSup;
    @ManyToMany
    @JoinTable(name ="EMP_GP",joinColumns =
    @JoinColumn(name = "CODE_EMP"),
    inverseJoinColumns = @JoinColumn(name = "CODE_GR"))
    private Collection<Groupe> groupes;

    public Employer() {
    }

    public Employer(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public Long getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(Long codeEmp) {
        this.codeEmp = codeEmp;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public Employer getEmployerSup() {
        return employerSup;
    }

    public void setEmployerSup(Employer employerSup) {
        this.employerSup = employerSup;
    }

    public Collection<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Collection<Groupe> groupes) {
        this.groupes = groupes;
    }
}
