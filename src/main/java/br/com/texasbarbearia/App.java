package br.com.texasbarbearia;

import java.util.List;
import java.util.Scanner;
import br.com.texasbarbearia.dao.ClienteDAO;
import br.com.texasbarbearia.model.Cliente;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO dao = new ClienteDAO();
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- MENU TEXAS BARBEARIA ---");
            System.out.println("1 - Cadastrar novo Cliente");
            System.out.println("2 - Listar todos os Clientes");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado para não dar erro

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("E-mail: ");
                String email = scanner.nextLine();

                Cliente novoCliente = new Cliente(nome, telefone, email);
                dao.cadastrar(novoCliente);

            } else if (opcao == 2) {
                System.out.println("\n--- LISTA DE CLIENTES ---");
                List<Cliente> clientes = dao.listarTodos();
                for (Cliente c : clientes) {
                    System.out.println("- " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
                }
            } else if (opcao != 3) {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close();
        System.out.println("Sistema encerrado. Até logo!");
    }
}