package br.com.texasbarbearia;

import java.util.List;
import java.util.Scanner;
import br.com.texasbarbearia.dao.ClienteDAO;
import br.com.texasbarbearia.model.Cliente;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDao = new ClienteDAO();
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n--- GESTÃO DE CLIENTES ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("5 - Sair");
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
                List<Cliente> clientes = clienteDao.listarTodos();
                for (Cliente c : clientes) {
                    System.out.println("ID: " + c.getId() + " | " + c.getNome() + " | " + c.getTelefone());
                }

            } else if (opcao == 3) {
                System.out.print("Digite o ID do cliente que deseja atualizar: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Novo E-mail: ");
                String email = scanner.nextLine();
                
                clienteDao.atualizar(new Cliente(id, nome, telefone, email));

            } else if (opcao == 4) {
                System.out.print("Digite o ID do cliente que deseja excluir: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
                clienteDao.excluir(id);

            } else if (opcao != 5) {
                System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}