package com.example;

public class TradicaoGaucha {
    private int id;
    private String nome;
    private String categoria;
    private String cidade;
    private Integer anoOrigem;

    public TradicaoGaucha(int id) {
        this.id = id;
    }

    public TradicaoGaucha(int id, String nome) {
        this(id);
        this.nome = nome;
    }

    public TradicaoGaucha(int id, String nome, String categoria) {
        this(id, nome);
        this.categoria = categoria;
    }

    public TradicaoGaucha(int id, String nome, String categoria, String cidade) {
        this(id, nome, categoria);
        this.cidade = cidade;
    }

    public TradicaoGaucha(int id, String nome, String categoria, String cidade, Integer anoOrigem) {
        this(id, nome, categoria, cidade);
        this.anoOrigem = anoOrigem;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCidade() {
        return cidade;
    }

    public Integer getAnoOrigem() {
        return anoOrigem;
    }

    public int quantidadeAtributosPreenchidos() {
        int quantidade = 1;
        if (nome != null && !nome.isBlank()) quantidade++;
        if (categoria != null && !categoria.isBlank()) quantidade++;
        if (cidade != null && !cidade.isBlank()) quantidade++;
        if (anoOrigem != null) quantidade++;
        return quantidade;
    }

    @Override
    public String toString() {
        return String.format("%-3d | %-28s | %-14s | %-20s | %s",
                id, valorOuTraco(nome), valorOuTraco(categoria),
                valorOuTraco(cidade), anoOrigem == null ? "-" : anoOrigem);
    }

    private String valorOuTraco(String valor) {
        return valor == null || valor.isBlank() ? "-" : valor;
    }
}
