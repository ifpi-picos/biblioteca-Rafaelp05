package dominio;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    // Construtor
    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null; // Devolução ainda não realizada
    }

    // Getters e Setters
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // Método para devolver livro
    public void devolverLivro(LocalDate dataDevolucao) {
        if (this.dataDevolucao == null) {
            this.dataDevolucao = dataDevolucao;
            livro.setEmprestado(false); // Marca o livro como disponível
            System.out.println("Livro devolvido com sucesso na data: " + dataDevolucao);
        } else {
            System.out.println("Este empréstimo já foi encerrado.");
        }
    }
}
