package br.com.texasbarbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.texasbarbearia.config.ConexaoFactory;
import br.com.texasbarbearia.model.Cliente;

public class ClienteDAO {
    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            
            stmt.execute();
            System.out.println("Cliente cadastrado com sucesso no banco de dados!");
            
        } catch (Exception e) {
            System.out.println("Erro ao guardar cliente: " + e.getMessage());
        }
    }
}