package org.gestion.bq.demo.metiers;

import org.gestion.bq.demo.dao.*;
import org.gestion.bq.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class IBanqueImp implements IBanqueMetier{
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepositoy userRepositoy;
    @Override
    public Compte ConsulterCompte(String codeCt) {
        Compte cp=compteRepository.findById(codeCt).orElse(null);
        if(cp==null) throw new RuntimeException("Compte introuvable! ");
        return cp;
    }

    @Override
    public Client ConsulterClient(Long codeCL) {
        Client client=clientRepository.findById(codeCL).orElse(null);
        if(client==null) throw new RuntimeException("Client introuvable! ");
        return client;
    }

    @Override
    public void vercer(String codeCt, double montant) {
        Compte cp=ConsulterCompte(codeCt);
        Vercement V=new Vercement(new Date(),montant,cp);
        operationRepository.save(V);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);

    }

    @Override
    public void retirer(String codeCt, double montant) {

        Compte cp=ConsulterCompte(codeCt);
        double fac=0;
        if (cp instanceof CompteCourant)
            fac=((CompteCourant) cp).getDecouvert();
        if(cp.getSolde()+fac<montant)
            throw new RuntimeException("Solde insuffisant");
        Retrait r=new Retrait(new Date(),montant,cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);

    }

    @Override
    public void virement(String codeCt1, String codeCt2, double montant) {

        if (codeCt1.equals(codeCt2))
            throw new RuntimeException("Impossible le Virement sur le méme compte!!");
        retirer(codeCt1,montant);
        vercer(codeCt2,montant);

    }

    @Override
    public Page<Operation> ListOperation(String codeCt, int page, int size) {

        return operationRepository.ListOperation(codeCt, PageRequest.of(page,size));

    }

    @Override
    public Page<Client> ConsulterClient(String keyword,int page, int size) {
        return clientRepository.ConsulterClient(keyword,PageRequest.of(page, size));
    }

    @Override
    public Page<Compte> ConsulterCompte1(String codeCt, int page, int size) {
        return compteRepository.ConsulterCompte1(codeCt,PageRequest.of(page, size));
    }

    @Override
    public Client saveClients(Client client) {

        clientRepository.save(client);
        return client;
    }

    @Override
    public Compte saveComptes(Compte compte) {
        compteRepository.save(compte);
        return compte;
    }

    @Override
    public void deleteClient(Long codeCL) {
        clientRepository.deleteById(codeCL);
    }

    @Override
    public Client editClient(Long codeCL) {
        Client client=clientRepository.findById(codeCL).orElse(null);
        if(client==null) throw new RuntimeException("Client introuvable");
        return client;
    }

    @Override
    public Page<Compte> ListComptes(String codeCt, int page, int size) {
        return compteRepository.ListComptes(codeCt,PageRequest.of(page,size));
    }



    @Override
    public Compte saveCompte(Compte compte) {
        compteRepository.save(compte);
        return compte;
    }

    @Override
    public void deleteCompte(String codeCt) {
        compteRepository.deleteById(codeCt);
    }

    @Override
    public Compte editCompte(String codeCt) {
        Compte compte=compteRepository.findById(codeCt).orElse(null);
        if(compte==null) throw new RuntimeException("Compte introuvable");
        return compte;
    }

    @Override
    public Page<Utilisateurs> ConsulterByUsers(String keyword,int page,int size) {
        return userRepositoy.Consulterusers(keyword,PageRequest.of(page,size));
    }
    @Override
    public Utilisateurs editUser(Long id) {
        Utilisateurs utilisateurs=userRepositoy.findById(id).orElse(null);
        if(utilisateurs==null) throw new RuntimeException("Compte introuvable");
        return utilisateurs;
    }

    @Override
    public void deleteUser(Long id) {
        userRepositoy.deleteById(id);
    }

    @Override
    public Utilisateurs saveUser(Utilisateurs utilisateurs) {
        return userRepositoy.save(utilisateurs);
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public void saveRolesTouser(String username, String lastname, String email, String password, String role) {
        Utilisateurs utilisateurs=new Utilisateurs();
        utilisateurs.setUsername(username);
        utilisateurs.setLastname(lastname);
        utilisateurs.setEmail(email);
        utilisateurs.setPassword(password);
        utilisateurs=addRolesToUsers(utilisateurs,role);

        userRepositoy.save(utilisateurs);
    }

    @Override
    public void saveClientTocompte(String codeCt, Date DTcreation, double solde, String client) {
        Compte compte=new Compte();
        compte.setCodeCt(codeCt);
        compte.setDTcreation(DTcreation);
        compte.setSolde(solde);
        compte=addClientToCompte(compte,client);

        compteRepository.save(compte);
    }

    @Override
    public Utilisateurs addRolesToUsers(Utilisateurs utilisateurs, String role) {
        String [] rol=role.split(",");

        for (String str:rol){
            Roles roles=rolesRepository.findById(Long.parseLong(str)).get();
            Collection c=utilisateurs.getRole();//.add(roles);
            c.add(roles);
            utilisateurs.setRole(c);
        }
        return utilisateurs;
    }

    @Override
    public Compte addClientToCompte(Compte compte, String client) {
        String [] cl=client.split(",");

        for (String str:cl){

            Client client1=clientRepository.findById(Long.parseLong(str)).get();
            compte.setClient(client1);
        }
        return compte;
    }

    @Override
    public Compte AddClientToCompte(String codeCt, Long codeCL) {
        Client client=ConsulterClient(codeCL);
        Compte compte=ConsulterCompte(codeCt);
       client.getComptes().add(compte);
        return compte;
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void AddCompteCourant(String codeCt, Date DTcreation, double solde,Long codeCL, double decouvert) {

        CompteCourant compteCourant=new CompteCourant();
        compteCourant.setCodeCt(codeCt);
        compteCourant.setDTcreation(DTcreation);
        compteCourant.setSolde(solde);

        compteCourant.setDecouvert(decouvert);

        compteRepository.save(compteCourant);

    }

    @Override
    public void AddCompteEpagne(String codeCt, Date DTcreation, double solde,Long codeCL,double taux) {
        CompteEpagne compteEpagne=new CompteEpagne();
        compteEpagne.setCodeCt(codeCt);
        compteEpagne.setDTcreation(DTcreation);
        compteEpagne.setSolde(solde);

        compteEpagne.setTaux(taux);
        compteRepository.save(compteEpagne);
    }

    @Override
    public Integer getNumClient() {
        return clientRepository.getALLClient();
    }

    @Override
    public Integer getNumCompte() {
        return compteRepository.getALLCompte();
    }

    @Override
    public Integer getOpRetrait() {
        return operationRepository.getNumRetrait();
    }

    @Override
    public Integer getOpVercement() {
        return operationRepository.getNumDepot();
    }

    @Override
    public Page<Operation> ListOperationJour(int page, int size) {
        return operationRepository.ListOperationJour(PageRequest.of(page,size));
    }
}
