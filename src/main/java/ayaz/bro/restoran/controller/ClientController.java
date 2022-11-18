package ayaz.bro.restoran.controller;

import ayaz.bro.restoran.entity.Client;
import ayaz.bro.restoran.entity.Dish;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.service.ClientService;
import ayaz.bro.restoran.util.DataErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public Client getClient(@RequestParam(defaultValue = "id") String searchBy, @RequestParam(defaultValue = "1") String value) {
        if(searchBy.equals("id"))
            return clientService.findById(Integer.parseInt(value));
        else {
            return clientService.findByName(value);
        }
    }

    @GetMapping("/clients")
    public List<Client> getClients(@RequestParam(defaultValue = "id") String category) {
        return clientService.findAll(category);
    }

    @GetMapping("/{id}/dishes")
    public List<Dish> getDishes(@PathVariable int id) {
        return clientService.findById(id).getDishes();
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataNotFoundException e) {
        DataErrorResponse response=new DataErrorResponse(
                "Client doesn't found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//NOT FOUND - 404 status
    }
}
