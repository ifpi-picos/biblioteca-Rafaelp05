package main;

import dominio.Livro;
import dominio.Usuario;
import dominio.Emprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarLivrosPorStatus();
                    break;
                case 4:
                    cadastrarUsuario();
                    break;
                case 5:
                    emprestarLivro();
                    break;
                case 6:
                    devolverLivro();
                    break;
                case 7:
                    listarHistoricoUsuario();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema de Biblioteca ===");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Listar todos os livros");
        System.out.println("3. Listar livros por status (emprestados/disponíveis)");
        System.out.println("4. Cadastrar usuário");
        System.out.println("5. Emprestar livro");
        System.out.println("6. Devolver livro");
        System.out.println("7. Listar histórico de empréstimos de um usuário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarLivro() {
        System.out.println("\n--- Cadastro de Livro ---");

        System.out.print("Informe o título: ");
        String titulo = scanner.nextLine();

        System.out.print("Informe o autor: ");
        String autor = scanner.nextLine();

        System.out.print("Informe a editora: ");
        String editora = scanner.nextLine();

        System.out.print("Informe o ano de publicação: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        Livro livro = new Livro(autor, titulo, editora, ano);
        livros.add(livro);

        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void listarLivros() {
        System.out.println("\n--- Lista de Todos os Livros ---");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                livro.mostrarInformacoes();
                System.out.println("-------------------------");
            }
        }
    }

    private static void listarLivrosPorStatus() {
        System.out.println("\n--- Livros Emprestados e Disponíveis ---");

        System.out.println("\nLivros Disponíveis:");
        for (Livro livro : livros) {
            if (!livro.isEmprestado()) {
                System.out.println("- " + livro.getTitulo());
            }
        }

        System.out.println("\nLivros Emprestados:");
        for (Livro livro : livros) {
            if (livro.isEmprestado()) {
                System.out.println("- " + livro.getTitulo());
            }
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");

        System.out.print("Informe o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o CPF do usuário: ");
        String cpf = scanner.nextLine();

        System.out.print("Informe o email do usuário: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, cpf, email);
        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void emprestarLivro() {
        System.out.println("\n--- Empréstimo de Livro ---");

        System.out.print("Informe o título do livro a ser emprestado: ");
        String titulo = scanner.nextLine();

        Livro livroEncontrado = null;
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && !livro.isEmprestado()) {
                livroEncontrado = livro;
                break;
            }
        }

        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado ou já emprestado.");
            return;
        }

        System.out.print("Informe o CPF do usuário: ");
        String cpf = scanner.nextLine();

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equalsIgnoreCase(cpf)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        livroEncontrado.setEmprestado(true);
        Emprestimo emprestimo = new Emprestimo(livroEncontrado, usuarioEncontrado, LocalDate.now());
        emprestimos.add(emprestimo);

        System.out.println("Livro emprestado com sucesso!");
    }

    private static void devolverLivro() {
        System.out.println("\n--- Devolução de Livro ---");

        System.out.print("Informe o título do livro a ser devolvido: ");
        String titulo = scanner.nextLine();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(titulo) && emprestimo.getLivro().isEmprestado()) {
                emprestimo.devolverLivro(LocalDate.now());
                return;
            }
        }

        System.out.println("Livro não encontrado ou já devolvido.");
    }

    private static void listarHistoricoUsuario() {
        System.out.println("\n--- Histórico de Empréstimos ---");

        System.out.print("Informe o CPF do usuário: ");
        String cpf = scanner.nextLine();

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equalsIgnoreCase(cpf)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Histórico de empréstimos para " + usuarioEncontrado.getNome() + ":");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuarioEncontrado)) {
                System.out.println("- Livro: " + emprestimo.getLivro().getTitulo() + 
                                   " | Data de Empréstimo: " + emprestimo.getDataEmprestimo() +
                                   " | Data de Devolução: " + emprestimo.getDataDevolucao());
            }
        }
    }
}
