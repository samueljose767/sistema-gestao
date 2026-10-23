package br.com.texasbarbearia.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.texasbarbearia.api.dao.AgendamentoDAO;
import br.com.texasbarbearia.api.model.Agendamento;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private AgendamentoDAO agendamentoDao = new AgendamentoDAO();

    @GetMapping
    public List<Agendamento> listarAgendamentos() {
        return agendamentoDao.listarTodos();
    }

    // Exemplo: http://localhost:8081/agendamentos/faturamento/diario?data=2026-10-23
    @GetMapping("/faturamento/diario")
    public double faturamentoDiario(@RequestParam String data) {
        return agendamentoDao.calcularFaturamentoDiario(data);
    }

    // Exemplo: http://localhost:8081/agendamentos/faturamento/semanal?inicio=2026-10-19&fim=2026-10-25
    @GetMapping("/faturamento/semanal")
    public double faturamentoSemanal(@RequestParam String inicio, @RequestParam String fim) {
        // Se o seu método no DAO tiver outro nome, altere 'calcularFaturamentoSemanal' aqui:
        return agendamentoDao.calcularFaturamentoSemanal(inicio, fim);
    }

    // Exemplo: http://localhost:8081/agendamentos/faturamento/mensal?anoMes=2026-10
    @GetMapping("/faturamento/mensal")
    public double faturamentoMensal(@RequestParam String anoMes) {
        return agendamentoDao.calcularFaturamentoMensal(anoMes);
    }
}