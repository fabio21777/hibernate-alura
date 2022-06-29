package org.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class Categoria  {
    @EmbeddedId
    CategoriaId id;

    public Categoria(String nome) {
        this.id = new CategoriaId(1L, nome);
    }

    public Categoria() {
    }

    public String getNome() {
        return this.id.getNome();
    }

    public void setNome(String nome) {
        this.id.setNome(nome);
    }
}
