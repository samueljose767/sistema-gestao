package br.com.texasbarbearia;

import java.util.List;
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

        while (opcao != 4) {
            System.out.println("\n--- MENU TEXAS BARBEARIA ---");
            System.out.println("1 - Cadastrar novo Cliente");
            System.out.println("2 - Listar todos os Clientes");
            System.out.println("3 - Cadastrar novo Serviço");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();

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
                List<Cliente> clientes = clienteDao.listarTodos();
                for (Cliente c : clientes) {
                    System.out.println("- " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
                }
            } else if (opcao == 3) {
                System.out.print("Descrição do Serviço (ex: Corte Degradê): ");
                String descricao = scanner.nextLine();
                System.out.print("Valor do Serviço (ex: 35,00 - use vírgula): ");
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpa o buffer

                servicoDao.cadastrar(new Servico(descricao, valor));

            } else if (opcao != 4) {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close();
        System.out.println("Sistema encerrado. Até logo!");
    }
}