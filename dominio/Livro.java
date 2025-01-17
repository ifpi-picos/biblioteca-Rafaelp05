package dominio;

public class Livro{
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private boolean emprestado;

    public Livro(String autor, String titulo, String editora, int ano){
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.emprestado = false;
    }

    public String getAutor(){
        return autor;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getEditora(){
        return editora;
    }

    public int getAno(){
        return ano;
    }

    public boolean isEmprestado(){
        return emprestado;
    }

    public void setEmprestado(boolean emprestado){
        this.emprestado = emprestado;
    }

    public void mostrarInformacoes(){
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Editora: " + editora);
        System.out.println("Ano: " + ano);
        System.out.println("Emprestado: " + (emprestado ? "Sim" : "Não"));
    }
}