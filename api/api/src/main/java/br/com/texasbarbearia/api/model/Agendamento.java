package br.com.texasbarbearia.api.model;

public class Agendamento {
    private Integer id;
    private int clienteId;
    private int servicoId;
    private String dataHora;
    
    private String nomeCliente;
    private String descricaoServico;
    private String status; // Nova variável

    // Construtor para Inserir (O MySQL preenche o status como 'AGENDADO' por padrão)
    public Agendamento(int clienteId, int servicoId, String dataHora) {
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataHora = dataHora;
    }

    // Construtor para Listar (agora recebe o status)
    public Agendamento(Integer id, String nomeCliente, String descricaoServico, String dataHora, String status) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.descricaoServico = descricaoServico;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Integer getId() { return id; }
    public int getClienteId() { return clienteId; }
    public int getServicoId() { return servicoId; }
    public String getDataHora() { return dataHora; }
    public String getNomeCliente() { return nomeCliente; }
    public String getDescricaoServico() { return descricaoServico; }
    public String getStatus() { return status; }
}

