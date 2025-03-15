package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

public class Usuario{
    private int id_usuario;
    private String nome;
    private String cpf;
    private String email;
    private List<Emprestimo> historicoDeEmprestimos;

    public Usuario(String nome, String cpf, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.historicoDeEmprestimos = new ArrayList<>();
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void addEmprestimoHistorico(Emprestimo novoEmprestimo){
        historicoDeEmprestimos.add(novoEmprestimo);
    }

    public void listarHistoricoDeEmprestimos(){
        System.out.println("-Histórico de empréstimo do usuário " + this.nome);
        for(Emprestimo emprestimo : historicoDeEmprestimos){
            System.out.println("Título do livro emprestado: " + emprestimo.getTituloLivroEmprestado());
            System.out.println("Data do empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
        }
    }
}