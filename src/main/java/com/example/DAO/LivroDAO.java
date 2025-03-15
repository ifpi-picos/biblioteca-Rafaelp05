package com.example.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dominio.Livro;

public class LivroDAO {
    private Connection connection;

    public LivroDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarLivro(Livro livro) throws SQLException{
        String sql = "INSERT INTO livro(autor, titulo, editora, ano, emprestado) VALUES(?, ?, ?, ?, FALSE)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livro.getAutor());
            statement.setString(2, livro.getTitulo());
            statement.setString(3, livro.getEditora());
            statement.setInt(4, livro.getAno());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void removerLivro(Livro livro) throws SQLException{
        String sql = "DELETE FROM livros WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, livro.getId_livro());
            statement.executeUpdate();
            System.out.println("Livro removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover o livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void atualizarLivro(Livro livro) throws SQLException{
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano = ? emprestado = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getEditora());
            statement.setInt(4, livro.getAno());
            statement.setBoolean(5, livro.isEmprestado());
            statement.setInt(6, livro.getId_livro());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro.");
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() throws SQLException{
        System.out.println("Livros da biblioteca");
        String sql = "SELECT id, titulo, autor, editora, ano FROM livros";
        List<Livro> livros = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Livro livro = new Livro(result.getString("Titulo"), result.getString("autor"), result.getString("editora"), result.getInt("ano"));
                livro.setId_livro(result.getInt("id"));
                livros.add(livro);
            }
            return livros;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Livro> listarLivroStatus() throws SQLException{
        System.out.println("Lista de livros por status");
        String sql = "SELECT id, titulo, autor, editora, ano, emprestado FROM livros";
        List<Livro> livros = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Livro livro = new Livro(
                    result.getString("titulo"),
                    result.getString("autor"),
                    result.getString("editora"),
                    result.getInt("ano")
                );
                livro.setEmprestado(result.getBoolean("emprestado"));
                livro.setId_livro(result.getInt("id"));
                livros.add(livro);
            }
            return livros;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Livro pesquisarLivroTipo(String titulo) throws SQLException{
        String sql = "SELECT id, titulo, autor, editora, ano FROM livros WHERE titulo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                Livro livro = new Livro (
                    result.getString("titulo"),
                    result.getString("autor"),
                    result.getString("editora"),
                    result.getInt("ano"));
                    livro.setId_livro(result.getInt("id"));
                    System.out.println("Livro encontrado por título");
                    return livro;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar o livro pelo título " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Livro não encontrado por título, tente novamente.");
        return null;
    }

    public Livro pesquisarLivroId(int id) throws SQLException {
        String sql = "SELECT titulo, autor, editora, ano FROM livros WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Livro livro = new Livro(
                        result.getString("titulo"),
                        result.getString("autor"),
                        result.getString("editora"),
                        result.getInt("ano"));
                livro.setId_livro(id);
                System.out.println("Livro encontrado por id!");
                return livro;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar livro por id : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Livro não encontrado por id, tente novamente.");
        return null;
    }
}