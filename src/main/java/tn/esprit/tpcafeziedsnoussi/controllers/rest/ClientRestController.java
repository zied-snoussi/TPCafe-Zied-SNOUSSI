package tn.esprit.tpcafeziedsnoussi.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpcafeziedsnoussi.entities.Client;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientRestController {
    IClientService clientService;

    @GetMapping
    public List<Client> selectAllClients() {
        return clientService.selectAllClients();
    }

    @GetMapping("/{id}")
    public Client selectClientByIdWithOrElse(@PathVariable Long id) {
        return clientService.selectClientByIdWithOrElse(id);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping
    public void deleteClient(@RequestBody Client client) {
        clientService.deleteClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }

    @GetMapping("/verif/{id}")
    public boolean verifClientById(@PathVariable Long id) {
        return clientService.verifClientById(id);
    }

    @PatchMapping("/{id}")
    public Client updateClientById(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClientById(id, client);
    }
}
