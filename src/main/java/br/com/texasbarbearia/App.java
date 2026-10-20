package br.com.texasbarbearia;

import java.util.Scanner;
import br.com.texasbarbearia.dao.ClienteDAO;
import br.com.texasbarbearia.dao.ServicoDAO;
import br.com.texasbarbearia.model.Cliente;
import br.com.texasbarbearia.model.Servico;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDao = new ClienteDAO();
        ServicoDAO servicoDao = new ServicoDAO();
        int opcao = 0;

        while (opcao != 9) {
            System.out.println("\n--- GESTÃO TEXAS BARBEARIA ---");
            System.out.println("1 - Cadastrar Cliente   | 5 - Cadastrar Serviço");
            System.out.println("2 - Listar Clientes     | 6 - Listar Serviços");
            System.out.println("3 - Atualizar Cliente   | 7 - Atualizar Serviço");
            System.out.println("4 - Excluir Cliente     | 8 - Excluir Serviço");
            System.out.println("9 - Sair");
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
                System.out.print("Novo Valor (ex: 40,00): ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                servicoDao.atualizar(new Servico(id, descricao, valor));

            } else if (opcao == 8) {
                System.out.print("ID do serviço a excluir: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                servicoDao.excluir(id);

            } else if (opcao != 9) {
                System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}