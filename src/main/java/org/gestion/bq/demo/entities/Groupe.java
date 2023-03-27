package org.gestion.bq.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Groupe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CodeGp;
    private String nomGp;
    @ManyToMany(mappedBy = "groupes")

    private Collection<Employer> employers;

    public Groupe() {
    }

    public Groupe(String nomGp) {
        this.nomGp = nomGp;
    }

    public Long getCodeGp() {
        return CodeGp;
    }

    public void setCodeGp(Long codeGp) {
        CodeGp = codeGp;
    }

    public String getNomGp() {
        return nomGp;
    }

    public void setNomGp(String nomGp) {
        this.nomGp = nomGp;
    }

    public Collection<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Collection<Employer> employers) {
        this.employers = employers;
    }
}
