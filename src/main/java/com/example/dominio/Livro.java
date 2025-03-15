package com.example.dominio;

public class Livro{
    private int id_livro;
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private boolean emprestado = false;

    public Livro(String autor, String titulo, String editora, int ano){
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getAutor(){
        return autor;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getEditora(){
        return editora;
    }

    public void setEditora(String editora){
        this.editora = editora;
    }

    public int getAno(){
        return ano;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public boolean isEmprestado(){
        return emprestado;
    }

    public void setEmprestado(boolean emprestado){
        this.emprestado = emprestado;
    }
}