package org.example.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro extends Produto{
    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
