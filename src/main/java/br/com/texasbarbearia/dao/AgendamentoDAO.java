package br.com.texasbarbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.texasbarbearia.config.ConexaoFactory;
import br.com.texasbarbearia.model.Agendamento;

public class AgendamentoDAO {
    
    public void cadastrar(Agendamento agendamento) {
        String sql = "INSERT INTO agendamentos (cliente_id, servico_id, data_hora) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getClienteId());
            stmt.setInt(2, agendamento.getServicoId());
            stmt.setString(3, agendamento.getDataHora()); 
            stmt.execute();
            System.out.println("Agendamento realizado com sucesso na Texas Barbearia!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar agendamento: " + e.getMessage());
        }
    }

    public List<Agendamento> listarTodos() {
        String sql = "SELECT a.id, c.nome AS cliente_nome, s.descricao AS servico_desc, a.data_hora " +
                     "FROM agendamentos a " +
                     "INNER JOIN clientes c ON a.cliente_id = c.id " +
                     "INNER JOIN servicos s ON a.servico_id = s.id";
        
        List<Agendamento> agendamentos = new ArrayList<>();

        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                    rs.getInt("id"),
                    rs.getString("cliente_nome"),
                    rs.getString("servico_desc"),
                    rs.getString("data_hora")
                );
                agendamentos.add(agendamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }
}