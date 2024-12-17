package dominio;

public class Livro{
    //Atributos do livro
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private boolean emprestado;

    //Construtor, para criar os objetos da classe Livro 
    public Livro(String autor, String titulo, String editora, int ano){
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.emprestado = false; //Falso, pois o livro por padrão não está emprestado
    }

    //Geters, para acessar os atributos
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

    //Setters, para atualizar o status de emprestado
    public void setEmprestado(boolean emprestado){
        this.emprestado = emprestado;
    }

    //Exibir as informações do livro
    public void mostrarInformacoes(){
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Editora: " + editora);
        System.out.println("Ano: " + ano);
        System.out.println("Emprestado: " + (emprestado ? "Sim" : "Não"));
    }
}