package ayaz.bro.restoran.service;

import ayaz.bro.restoran.entity.Client;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    public Client findByName(String name) {
        return clientRepository.findClientByName(name)
                .orElseThrow(DataNotFoundException::new);
    }
    public List<Client> findAll(String category) {
        return clientRepository.findAll(Sort.by(category));
    }
}
