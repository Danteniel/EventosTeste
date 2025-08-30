package projeto;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaEventos svc = new SistemaEventos();

        int opcao;
        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Cadastrar evento");
            System.out.println("3. Listar eventos");
            System.out.println("4. Participar de evento");
            System.out.println("5. Cancelar participação em evento");
            System.out.println("6. Listar eventos confirmados de um usuário"); 
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarUsuario(svc);
                case 2 -> cadastrarEvento(svc);
                case 3 -> listarEventosComFiltros(svc);
                case 4 -> participarEvento(svc);
                case 5 -> cancelarParticipacao(svc);
                case 6 -> listarEventosUsuario(svc);
                case 0 -> {
                    svc.salvarTudo();
                    System.out.println("Encerrando o sistema...");
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarUsuario(SistemaEventos svc) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerIntSeguro();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        svc.cadastrarUsuario(nome, email, idade, telefone, senha);
        pausar();
    }

    private static void cadastrarEvento(SistemaEventos svc) {
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Ano: ");
        int ano = lerIntSeguro();
        System.out.print("Mês: ");
        int mes = lerIntSeguro();
        System.out.print("Dia: ");
        int dia = lerIntSeguro();
        System.out.print("Hora: ");
        int hora = lerIntSeguro();
        System.out.print("Minuto: ");
        int minuto = lerIntSeguro();

        svc.cadastrarEvento(nome, endereco, categoria, ano, mes, dia, hora, minuto, descricao);
        pausar();
    }

    private static void listarEventosComFiltros(SistemaEventos svc) {
        System.out.println("\n-- Listagem de eventos --");
        System.out.println("1) Todos");
        System.out.println("2) Por categoria");
        System.out.println("3) Passados");
        System.out.println("4) Hoje (em andamento, mesmo dia)");
        System.out.println("5) Futuros");
        System.out.print("Escolha: ");
        int escolha = lerIntSeguro();
        switch (escolha) {
            case 1 -> svc.listarEventosTodos();
            case 2 -> {
                System.out.print("Digite a categoria: ");
                String cat = new Scanner(System.in).nextLine();
                svc.listarEventosPorCategoria(cat);
            }
            case 3 -> svc.listarEventosPassados();
            case 4 -> svc.listarEventosHoje();
            case 5 -> svc.listarEventosFuturos();
            default -> System.out.println("Opção inválida!");
        }
        pausar();
    }

    private static void participarEvento(SistemaEventos svc) {
        svc.participarEventoViaConsole(scanner);
        pausar();
    }

    private static void cancelarParticipacao(SistemaEventos svc) {
        svc.cancelarParticipacaoViaConsole(scanner);
        pausar();
    }

    private static void listarEventosUsuario(SistemaEventos svc) {
        svc.listarEventosUsuarioViaConsole(scanner);
        pausar();
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }

    private static int lerIntSeguro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); 
        return val;
    }
}