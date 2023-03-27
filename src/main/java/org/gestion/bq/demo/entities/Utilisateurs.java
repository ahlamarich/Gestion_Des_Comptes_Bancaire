package org.gestion.bq.demo.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Iterator;

@Entity
@Table(name = "user2",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Entez Votre username")
    private String username;
    @NotBlank(message = "Entez Votre lastname")
    private String lastname;
    @NotBlank(message = "Entez Votre email")
    @Email(message = "Entrez une adresse Email validée")
    private String email;
    @NotBlank(message = "Entez Votre password")
    @Length(min = 6,message = "Mot de passe doit être d'au moins 6 caractères")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles2",
            joinColumns = @JoinColumn(
                    name = "user2_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role2_id", referencedColumnName = "id"))
    private Collection<Roles> role;

    public Utilisateurs() {
        role=new Collection<Roles>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Roles> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Roles roles) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Roles> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }

    public Utilisateurs(String username, String lastname, String email, String password, Collection<Roles> role) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Collection<Roles> getRole() {
        return role;
    }

    public void setRole(Collection<Roles> role) {
        this.role = role;
    }
}
