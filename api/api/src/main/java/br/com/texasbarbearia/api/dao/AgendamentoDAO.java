package br.com.texasbarbearia.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.texasbarbearia.api.config.ConexaoFactory;
import br.com.texasbarbearia.api.model.Agendamento;

public class AgendamentoDAO {
    
    public void cadastrar(Agendamento agendamento) {
        String sql = "INSERT INTO agendamentos (cliente_id, servico_id, data_hora) VALUES (?, ?, ?)";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getClienteId());
            stmt.setInt(2, agendamento.getServicoId());
            stmt.setString(3, agendamento.getDataHora()); 
            stmt.execute();
            System.out.println("Agendamento realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao realizar agendamento: " + e.getMessage());
        }
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE agendamentos SET status = ? WHERE id = ?";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);
            stmt.execute();
            System.out.println("Status do agendamento atualizado para " + novoStatus + "!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
        }
    }

    public boolean verificarHorarioOcupado(String dataHora) {
        String sql = "SELECT COUNT(*) FROM agendamentos WHERE data_hora = ? AND status != 'CANCELADO'";
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, dataHora);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar disponibilidade: " + e.getMessage());
        }
        return false;
    }

    public List<Agendamento> listarTodos() {
        String sql = "SELECT a.id, c.nome AS cliente_nome, s.descricao AS servico_desc, a.data_hora, a.status " +
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
                    rs.getString("data_hora"),
                    rs.getString("status") 
                );
                agendamentos.add(agendamento);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }

    public double calcularFaturamentoDiario(String data) {
        String sql = "SELECT SUM(s.valor) FROM agendamentos a " +
                     "INNER JOIN servicos s ON a.servico_id = s.id " +
                     "WHERE a.status = 'CONCLUIDO' AND DATE(a.data_hora) = ?";
        
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1); 
            }
        } catch (Exception e) {
            System.out.println("Erro ao calcular faturamento: " + e.getMessage());
        }
        return 0.0;
    }

    public double calcularFaturamentoMensal(String anoMes) {
        String sql = "SELECT SUM(s.valor) FROM agendamentos a " +
                     "INNER JOIN servicos s ON a.servico_id = s.id " +
                     "WHERE a.status = 'CONCLUIDO' AND DATE_FORMAT(a.data_hora, '%Y-%m') = ?";
        
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setString(1, anoMes);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao calcular faturamento mensal: " + e.getMessage());
        }
        return 0.0;
    }

    public double calcularFaturamentoSemanal(String data) {
        String sql = "SELECT SUM(s.valor) FROM agendamentos a " +
                     "INNER JOIN servicos s ON a.servico_id = s.id " +
                     "WHERE a.status = 'CONCLUIDO' AND YEARWEEK(a.data_hora, 1) = YEARWEEK(?, 1)";
        
        try (Connection conexao = ConexaoFactory.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao calcular faturamento semanal: " + e.getMessage());
        }
        return 0.0;
    }
}