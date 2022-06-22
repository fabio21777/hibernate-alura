package org.example.model.produto;

public enum Categoria {
    CELULARES ("Celulares"),
    INFORMATICA("Informática"),
    LIVROS("Livros");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }
}
