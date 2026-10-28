package br.com.texasbarbearia.api.model;

public class Cliente {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;

    // Construtor vazio exigido pelo Spring Boot (Jackson) para receber dados JSON
    public Cliente() {
    }

    // Construtor para Inserir (sem ID, pois o MySQL gera automaticamente)
    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor para Listar e Atualizar (com ID)
    public Cliente(Integer id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
}