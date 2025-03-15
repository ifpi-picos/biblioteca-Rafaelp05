package com.example.dominio;

import java.time.LocalDate;

public class Emprestimo {
    private int id_emprestimo;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido = false;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(7);
        this.devolvido = false;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public Livro getLivro(){
        return this.livro;
    }

    public int getId_usuario(){
        return usuario.getId_usuario();
    }

    public void setId_usuario(int id_usuario) {
        this.usuario.setId_usuario(id_usuario);
    }

    public int getId_livro() {
        return livro.getId_livro();
    }

    public void setId_livro(int id_livro) {
        this.livro.setId_livro(id_livro);
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }
    
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
        
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    
    public String getTituloLivroEmprestado() {
        return livro.getTitulo();
    }
    
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }
}