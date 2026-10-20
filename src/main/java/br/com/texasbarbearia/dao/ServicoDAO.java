package br.com.texasbarbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.texasbarbearia.config.ConexaoFactory;
import br.com.texasbarbearia.model.Servico;

public class ServicoDAO {
    
    public void cadastrar(Servico servico) {
        String sql = "INSERT INTO servicos (descricao, valor) VALUES (?, ?)";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getValor());
            stmt.execute();
            System.out.println("Serviço cadastrado com sucesso no sistema!");
            
        } catch (Exception e) {
            System.out.println("Erro ao guardar serviço: " + e.getMessage());
        }
    }

    public List<Servico> listarTodos() {
        String sql = "SELECT * FROM servicos";
        List<Servico> servicos = new ArrayList<>();

        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico(
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
                servicos.add(servico);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        }
        return servicos;
    }
}