package br.com.texasbarbearia.api.model;

public class Servico {
    private Integer id;
    private String descricao;
    private double valor;

    // Construtor para Inserir (sem ID)
    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    // Construtor para Listar, Atualizar e Excluir (com ID)
    public Servico(Integer id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}