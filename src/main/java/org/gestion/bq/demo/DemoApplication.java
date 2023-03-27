package org.gestion.bq.demo;

import org.gestion.bq.demo.dao.ClientRepository;
import org.gestion.bq.demo.dao.CompteRepository;
import org.gestion.bq.demo.dao.OperationRepository;
import org.gestion.bq.demo.entities.*;
import org.gestion.bq.demo.metiers.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Client c1=clientRepository.save(new Client("Hassan","hassan@gmail.com"));
		Client c2=clientRepository.save(new Client("ali","ali@gmail.com"));

		Compte cp1=compteRepository.save(new CompteCourant("cc1",new Date(),9000.0,c1,6000));
		Compte cp2=compteRepository.save(new CompteCourant("cc2",new Date(),7900.0,c2,6000));


		operationRepository.save(new Vercement(new Date(),9000.0,cp1));
		operationRepository.save(new Vercement(new Date(),6000.0,cp1));
		operationRepository.save(new Vercement(new Date(),2300.0,cp1));
		operationRepository.save(new Retrait(new Date(),9000.0,cp1));

		operationRepository.save(new Vercement(new Date(),2300.0,cp2));
		operationRepository.save(new Vercement(new Date(),6000.0,cp2));
		operationRepository.save(new Vercement(new Date(),2300.0,cp2));
		operationRepository.save(new Retrait(new Date(),9000.0,cp2));*/


		/*banqueMetier.vercer("cc1",111111);
		banqueMetier.vercer("cc2",111111);
        Client c3=clientRepository.save(new Client("mouad","mouad@gmail.com"));
        Compte cp3=compteRepository.save(new CompteEpagne("ce1",new Date(),4000.0,c3,5.3));
*/

	}
}
