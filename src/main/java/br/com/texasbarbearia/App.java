package br.com.texasbarbearia;

import java.util.Scanner;
import br.com.texasbarbearia.dao.ClienteDAO;
import br.com.texasbarbearia.dao.ServicoDAO;
import br.com.texasbarbearia.dao.AgendamentoDAO;
import br.com.texasbarbearia.model.Cliente;
import br.com.texasbarbearia.model.Servico;
import br.com.texasbarbearia.model.Agendamento;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDao = new ClienteDAO();
        ServicoDAO servicoDao = new ServicoDAO();
        AgendamentoDAO agendamentoDao = new AgendamentoDAO();
        int opcao = 0;

        while (opcao != 13) {
            System.out.println("\n--- GESTÃO TEXAS BARBEARIA ---");
            System.out.println("1 - Cadastrar Cliente   | 5 - Cadastrar Serviço");
            System.out.println("2 - Listar Clientes     | 6 - Listar Serviços");
            System.out.println("3 - Atualizar Cliente   | 7 - Atualizar Serviço");
            System.out.println("4 - Excluir Cliente     | 8 - Excluir Serviço");
            System.out.println("9 - Novo Agendamento    | 10 - Listar Agendamentos");
            System.out.println("11 - Atualizar Status   | 12 - Relatórios Financeiros");
            System.out.println("13 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("E-mail: ");
                String email = scanner.nextLine();
                clienteDao.cadastrar(new Cliente(nome, telefone, email));

            } else if (opcao == 2) {
                System.out.println("\n--- LISTA DE CLIENTES ---");
                for (Cliente c : clienteDao.listarTodos()) {
                    System.out.println("ID: " + c.getId() + " | " + c.getNome() + " | " + c.getTelefone());
                }

            } else if (opcao == 3) {
                System.out.print("ID do cliente a atualizar: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Novo E-mail: ");
                String email = scanner.nextLine();
                clienteDao.atualizar(new Cliente(id, nome, telefone, email));

            } else if (opcao == 4) {
                System.out.print("ID do cliente a excluir: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                clienteDao.excluir(id);

            } else if (opcao == 5) {
                System.out.print("Descrição do Serviço: ");
                String descricao = scanner.nextLine();
                System.out.print("Valor do Serviço (ex: 35,00): ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                servicoDao.cadastrar(new Servico(descricao, valor));

            } else if (opcao == 6) {
                System.out.println("\n--- LISTA DE SERVIÇOS ---");
                for (Servico s : servicoDao.listarTodos()) {
                    System.out.println("ID: " + s.getId() + " | " + s.getDescricao() + " | R$ " + s.getValor());
                }

            } else if (opcao == 7) {
                System.out.print("ID do serviço a atualizar: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nova Descrição: ");
                String descricao = scanner.nextLine();
                System.out.print("Novo Valor: ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                servicoDao.atualizar(new Servico(id, descricao, valor));

            } else if (opcao == 8) {
                System.out.print("ID do serviço a excluir: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                servicoDao.excluir(id);

            } else if (opcao == 9) {
                System.out.println("\n--- NOVO AGENDAMENTO ---");
                System.out.print("ID do Cliente: ");
                int idCliente = scanner.nextInt();
                System.out.print("ID do Serviço: ");
                int idServico = scanner.nextInt();
                scanner.nextLine(); 
                System.out.print("Data e Hora (ex: 2026-10-25 14:30): ");
                String dataHora = scanner.nextLine();
                
                if (agendamentoDao.verificarHorarioOcupado(dataHora)) {
                    System.out.println("❌ ERRO: Já existe um agendamento ativo para este horário! Escolha outro.");
                } else {
                    agendamentoDao.cadastrar(new Agendamento(idCliente, idServico, dataHora));
                }

            } else if (opcao == 10) {
                System.out.println("\n--- LISTA DE AGENDAMENTOS ---");
                for (Agendamento a : agendamentoDao.listarTodos()) {
                    System.out.println("ID: " + a.getId() + " | Cliente: " + a.getNomeCliente() + 
                                       " | Serviço: " + a.getDescricaoServico() + " | Data: " + a.getDataHora() + 
                                       " | Status: [" + a.getStatus() + "]");
                }

            } else if (opcao == 11) {
                System.out.println("\n--- ATUALIZAR STATUS ---");
                System.out.print("Digite o ID do Agendamento: ");
                int idAgendamento = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                
                System.out.println("Escolha o novo status:");
                System.out.println("1 - CONCLUÍDO (Cliente pagou)");
                System.out.println("2 - CANCELADO (Cliente faltou)");
                System.out.print("Opção: ");
                int opStatus = scanner.nextInt();
                scanner.nextLine();
                
                if (opStatus == 1) {
                    agendamentoDao.atualizarStatus(idAgendamento, "CONCLUIDO");
                } else if (opStatus == 2) {
                    agendamentoDao.atualizarStatus(idAgendamento, "CANCELADO");
                } else {
                    System.out.println("Status inválido.");
                }

            } else if (opcao == 12) {
                System.out.println("\n--- RELATÓRIOS DE FATURAMENTO ---");
                System.out.println("1 - Diário");
                System.out.println("2 - Semanal");
                System.out.println("3 - Mensal");
                System.out.print("Escolha o tipo de relatório: ");
                
                int tipoRelatorio = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                
                if (tipoRelatorio == 1) {
                    System.out.print("Digite a data (Formato YYYY-MM-DD, ex: 2026-10-23): ");
                    String data = scanner.nextLine();
                    double total = agendamentoDao.calcularFaturamentoDiario(data);
                    System.out.println("💰 Faturamento Diário (" + data + "): R$ " + total);
                    
                } else if (tipoRelatorio == 2) {
                    System.out.print("Digite uma data qualquer dessa semana (YYYY-MM-DD, ex: 2026-10-23): ");
                    String data = scanner.nextLine();
                    double total = agendamentoDao.calcularFaturamentoSemanal(data);
                    System.out.println("💰 Faturamento Semanal: R$ " + total);
                    
                } else if (tipoRelatorio == 3) {
                    System.out.print("Digite o ano e mês (Formato YYYY-MM, ex: 2026-10): ");
                    String anoMes = scanner.nextLine();
                    double total = agendamentoDao.calcularFaturamentoMensal(anoMes);
                    System.out.println("💰 Faturamento Mensal (" + anoMes + "): R$ " + total);
                    
                } else {
                    System.out.println("Opção de relatório inválida.");
                }

            } else if (opcao != 13) {
                System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}