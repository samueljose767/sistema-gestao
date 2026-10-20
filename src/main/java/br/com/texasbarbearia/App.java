package br.com.texasbarbearia;

import br.com.texasbarbearia.dao.ClienteDAO;
import br.com.texasbarbearia.model.Cliente;

public class App {
    public static void main(String[] args) {
        Cliente novoCliente = new Cliente("Samuel", "(61) 99999-0000", "samuel@email.com");
        ClienteDAO dao = new ClienteDAO();
        dao.cadastrar(novoCliente);
    }
}