package org.gestion.bq.demo.metiers;

import org.gestion.bq.demo.entities.*;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface IBanqueMetier {

    public Compte ConsulterCompte(String codeCt);
    public Page<Compte> ConsulterCompte1(String codeCt,int page,int size);
    public void vercer(String codeCt,double montant);
    public void retirer(String codeCt,double montant);
    public void virement(String codeCt1,String codeCt2,double montant);
    public Page<Operation> ListOperation(String codeCt,int page,int size);

    public Page<Client> ConsulterClient(String keyword,int page,int size);
    public Client saveClients(Client client);
    public Compte saveComptes(Compte compte);
    public void deleteClient(Long codeCL);
    public Client editClient(Long codeCL);
    public Page<Compte> ListComptes(String codeCt,int page,int size);

    public Compte saveCompte(Compte compte);
    public void deleteCompte(String codeCt);
    public Compte editCompte(String codeCt);

    public Page<Utilisateurs> ConsulterByUsers(String keyword,int page,int size);
    public Utilisateurs editUser(Long id);
    public void deleteUser(Long id);
    public Utilisateurs saveUser(Utilisateurs utilisateurs);

    public List<Roles> getAllRoles();

    public Utilisateurs addRolesToUsers(Utilisateurs utilisateurs,String role);
    public Compte addClientToCompte(Compte compte,String client);
    public void saveRolesTouser(String username,String lastname,String email,String password,String role);

    public void saveClientTocompte(String codeCt,Date DTcreation,double solde,String client);

    public List<Client> getAllClient();

    public Client ConsulterClient(Long codeCL);

    public void AddCompteCourant(String codeCt, Date DTcreation, double solde, Long codeCL, double decouvert);
    public void AddCompteEpagne(String codeCt, Date DTcreation, double solde,Long codeCL,double taux);
    public Compte AddClientToCompte(String codeCt,Long codeCL);

    public Integer getNumClient();
    public Integer getNumCompte();
    public Integer getOpRetrait();
    public Integer getOpVercement();

    public Page<Operation> ListOperationJour(int page,int size);

}
