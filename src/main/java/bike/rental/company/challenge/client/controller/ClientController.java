package bike.rental.company.challenge.client.controller;

import bike.rental.company.challenge.client.dao.ClientRepository;
import bike.rental.company.challenge.client.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ClientRepository clientRepository;

    @Autowired
    public ClientController(
            ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    
    public boolean createClient(String email,String ssn,String name, String surname,
                                String address, String zipCode) {
        Client client = new Client(email,ssn,name,surname,address,zipCode);
        clientRepository.save(client);
        return true;
    }
}
