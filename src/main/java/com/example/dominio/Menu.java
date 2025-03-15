package com.example.dominio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.example.DAO.EmprestimoDAO;
import com.example.DAO.LivroDAO;
import com.example.DAO.UsuarioDAO;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Connection connection;

    public Menu(Connection connection){
        this.connection = connection;
    }

    public void menuText(){
        System.out.println("Menu Biblioteca");
        System.out.println("1.Cadastrar Usuário \n 2.Cadastrar Livro \n 3.Listar Todos os Livros \n 4.Listar Livros cadastrados e Disponíveis \n 5.Listar Histórico de Empréstimo de Usuário \n 6.Realizar Empréstimo \n 7.Devolver Livro \n 0.Sair");
    }

    public void cadastrarUsuario() throws SQLException {
        System.out.println("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite o e-mail do usuário: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, cpf, email);
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioDAO.cadastrarUsuario(usuario);
    }

    public void cadastrarLivro() throws SQLException{
        System.out.println("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do autor: ");
        String autor = scanner.nextLine();

        System.out.println("Digite a editora do livro: ");
        String editora = scanner.nextLine();

        System.out.println("Digite o ano de lançamento do livro: ");
        int ano = scanner.nextInt();

        Livro livro = new Livro(autor, titulo, editora, ano);
        LivroDAO livroDAO = new LivroDAO(connection);
        livroDAO.cadastrarLivro(livro);
    }

    public void listarLivro() throws SQLException{
        LivroDAO livroDAO = new LivroDAO(connection);
        List<Livro> livros = livroDAO.listarLivros();
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano: " + livro.getAno());
        }
    }

    public void listarLivrosStatus() throws SQLException {
        LivroDAO livroDao = new LivroDAO(connection);
        List<Livro> livros = livroDao.listarLivroStatus();
        System.out.println("---- Livros Disponíveis ----");
        for (Livro livro : livros) {
            if (!livro.isEmprestado()) {
                System.out.println("Título -- " + livro.getTitulo());
                System.out.println("    Autor -- " + livro.getAutor());
                System.out.println("    Editora -- " + livro.getEditora());
                System.out.println("    Ano -- " + livro.getAno());
                System.out.println(" \n");
            }
        }
        System.out.println("---- Livros Emprestados ----");
        for (Livro livro : livros) {
            if (livro.isEmprestado()) {
                System.out.println("Título -- " + livro.getTitulo());
                System.out.println("    Autor -- " + livro.getAutor());
                System.out.println("    Editora -- " + livro.getEditora());
                System.out.println("    Ano -- " + livro.getAno());
                System.out.println(" \n");
                
            }
        }
    }

    public void listarHistoricoEmprestimos() throws SQLException{
        System.out.println("Digite o CPF do usuário:");
        String cpf = scanner.nextLine();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        Usuario usuario = usuarioDAO.pesquisarUsuarioCpf(cpf);
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO(connection);
        if(usuario != null){
            mostrarInformacoesEmprestimo(usuario, emprestimoDAO.listarHistoricoEmprestimos(usuario));
        }else{
            System.out.println("Usuário não encontrado");
        }
    }

    public void mostrarInformacoesEmprestimo(Usuario usuario, List<Emprestimo> emprestimos) throws SQLException{
        System.out.println("Histórico de empréstimos do usuário" + usuario.getNome());
        LivroDAO livroDAO = new LivroDAO(connection);
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Título do Livro Emprestado -- " + livroDAO.pesquisarLivroId(emprestimo.getId_livro()).getTitulo());
            System.out.println("   - Data do Empréstimo -- " + emprestimo.getDataEmprestimo());
            System.out.println("   - Data de Devolução -- " + emprestimo.getDataDevolucao());
            System.out.println("   - Status Devolução -- " + emprestimo.getDevolvido());
        }
    }

    public void realizarEmprestimo() throws SQLException{
        System.out.println("Digite o título do livro que deseja pegar emprestado: ");
        String pesquisaTitulo = scanner.nextLine();

        LivroDAO livroDAO = new LivroDAO(connection);
        Livro livroPesquisado = livroDAO.pesquisarLivroTipo(pesquisaTitulo);

        System.out.println("Digite o CPF do usuário: ");
        String cpfPesquisadoEmprestimo = scanner.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        Usuario userEmprestimo = usuarioDAO.pesquisarUsuarioCpf(cpfPesquisadoEmprestimo);

        if (userEmprestimo == null) {
            System.out.println("Usuário não encontrado.");
        }
        if (livroPesquisado == null) {
            System.out.println("Livro não encontrado.");
        }

        Emprestimo emprestimo = new Emprestimo(userEmprestimo, livroPesquisado);
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO(connection);
        emprestimoDAO.registrarEmprestimo(emprestimo);
    }

    public void devolverEmprestimo() throws SQLException{
        System.out.println("Digite o título do livro que quer devolver: ");
        String pesquisaDevolucaoTitulo = scanner.nextLine();
        LivroDAO livroDAO = new LivroDAO(connection);
        Livro livroPesquisado = livroDAO.pesquisarLivroTipo(pesquisaDevolucaoTitulo);

        System.out.println("Digite o cpf do usuário: ");
        String cpfPesquisa = scanner.nextLine();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        Usuario usuarioDevolucao = usuarioDAO.pesquisarUsuarioCpf(cpfPesquisa);

        if (usuarioDevolucao == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (pesquisaDevolucaoTitulo == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        EmprestimoDAO emprestimoDAO = new EmprestimoDAO(connection);
        Emprestimo emprestimo = emprestimoDAO.buscarEmprestimoUsuarioLivro(usuarioDevolucao.getId_usuario(), livroPesquisado.getId_livro());

        if (emprestimo != null) {
            emprestimoDAO.removerEmprestimo(emprestimo);
        }else{
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public void iniciarMenu() throws SQLException{
        boolean isLoop = true;

        while (isLoop) {
            menuText();
            System.out.println("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    listarLivro();
                    break;
                case 4:
                    listarLivrosStatus();
                    break;
                case 5:
                    listarHistoricoEmprestimos();
                    break;
                case 6:
                    realizarEmprestimo();
                    break;
                case 7:
                    devolverEmprestimo();
                    break;
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    isLoop = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente");
                    break;
            }
        }
        scanner.close();
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão com banco de dados: " + e.getMessage());
        }
    }
}