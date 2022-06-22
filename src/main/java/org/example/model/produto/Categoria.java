package org.example.model.produto;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    private  String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
