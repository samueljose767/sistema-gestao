package br.com.texasbarbearia.api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.texasbarbearia.api.model.Cliente;
import br.com.texasbarbearia.api.dao.ClienteDAO; 

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteDAO clienteDao;

    public ClienteController() {
        this.clienteDao = new ClienteDAO();
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        // Agora usamos o nome exato que está no seu ClienteDAO
        return clienteDao.listarTodos(); 
    }

    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente novoCliente) {
        try {
            // Agora usamos o nome exato que está no seu ClienteDAO
            clienteDao.cadastrar(novoCliente); 
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente registado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao guardar cliente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao guardar cliente.");
        }
    }
}