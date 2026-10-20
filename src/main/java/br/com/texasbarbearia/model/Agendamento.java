package br.com.texasbarbearia.model;

public class Agendamento {
    private Integer id;
    private int clienteId;
    private int servicoId;
    private String dataHora;

    private String nomeCliente;
    private String descricaoServico;

    public Agendamento(int clienteId, int servicoId, String dataHora) {
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataHora = dataHora;
    }

    public Agendamento(Integer id, String nomeCliente, String descricaoServico, String dataHora) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.descricaoServico = descricaoServico;
        this.dataHora = dataHora;
    }

    public Integer getId() { return id; }
    public int getClienteId() { return clienteId; }
    public int getServicoId() { return servicoId; }
    public String getDataHora() { return dataHora; }
    public String getNomeCliente() { return nomeCliente; }
    public String getDescricaoServico() { return descricaoServico; }
}