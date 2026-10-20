package br.com.texasbarbearia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}