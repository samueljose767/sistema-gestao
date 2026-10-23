package br.com.texasbarbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao guardar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Agora puxamos o ID também
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ? WHERE id = ?";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getId());
            stmt.execute();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Cliente excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }
}