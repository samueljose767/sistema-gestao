package br.com.texasbarbearia.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.texasbarbearia.api.dao.ClienteDAO;
import br.com.texasbarbearia.api.model.Cliente;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteDAO clienteDao = new ClienteDAO();

    @GetMapping
    public List<Cliente> listarClientes() {
        // Reaproveitamos o método que você já tinha criado no terminal!
        return clienteDao.listarTodos();
    }
}