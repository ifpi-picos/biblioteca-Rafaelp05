package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.dominio.Emprestimo;
import com.example.dominio.Livro;
import com.example.dominio.Usuario;

public class EmprestimoDAO {
    private Connection connection;

    public EmprestimoDAO(Connection conexao) {
        this.connection = conexao;
    }

    public void registrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo(livro_titulo, usuario_cpf, data_emprestimo) VALUES(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emprestimo.getLivro().getTitulo());
            statement.setString(2, emprestimo.getUsuario().getCpf());
            statement.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    public void registrarDevolucao(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimos SET devolvido = TRUE WHERE id = ? AND devolvido = FALSE";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, emprestimo.getId_emprestimo());
        statement.executeUpdate();
        Livro livroAtualização = new LivroDAO(connection).pesquisarLivroId(emprestimo.getId_livro());
        livroAtualização.setEmprestado(false);

        LivroDAO livroDAO = new LivroDAO(connection);
        livroDAO.atualizarLivro(livroAtualização);
        System.out.println("Devolução feita com sucesso!");
    }

    public void removerEmprestimo(Emprestimo emprestimo) throws SQLException{
        String sql = "DELETE FROM emprestimos WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, emprestimo.getId_emprestimo());
        statement.executeUpdate();
        System.out.println("Livro removido com sucesso!");
    }

    public Emprestimo buscarEmprestimoUsuarioLivro(int id_usuario, int id_livro) throws SQLException{
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id_usuario = ? AND id_livro = ? AND devolvido = FALSE";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_usuario);
        statement.setInt(2, id_livro);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            Usuario usuario = new UsuarioDAO(connection).pesquisarUsuarioId(result.getInt("id_usuario"));
            Livro livro = new LivroDAO(connection).pesquisarLivroId(result.getInt("id_livro"));
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimo.setId_emprestimo(result.getInt("id"));
            emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
            emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
            emprestimo.setDevolvido(result.getBoolean("devolvido"));
            return emprestimo;
        }

        return null;
    }

    public Emprestimo pesquisarEmprestimoId(int id) throws SQLException{
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            Usuario usuario = new UsuarioDAO(connection).pesquisarUsuarioId(result.getInt("id_usuario"));
            Livro livro = new LivroDAO(connection).pesquisarLivroId(result.getInt("id_livro"));
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimo.setId_emprestimo(result.getInt("id"));
            emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
            emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
            emprestimo.setDevolvido(result.getBoolean("devolvido"));
            return emprestimo;
        }

        return null;
    }

    public List<Emprestimo> listarHistoricoEmprestimos (Usuario usuario) throws SQLException{
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id_usuario = ?";
        List<Emprestimo> historicoEmprestimos = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId_usuario());
        ResultSet result = statement.executeQuery();

        while(result.next()){
            Usuario usuarioBusca = new UsuarioDAO(connection).pesquisarUsuarioId(result.getInt("id_usuario"));
            Livro livro = new LivroDAO(connection).pesquisarLivroId(result.getInt("id_livro"));
            Emprestimo emprestimo = new Emprestimo(usuarioBusca, livro);
            emprestimo.setId_emprestimo(result.getInt("id"));
            emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
            emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
            emprestimo.setDevolvido(result.getBoolean("devolvido"));
            historicoEmprestimos.add(emprestimo);
        }

        return historicoEmprestimos;
    }
}
