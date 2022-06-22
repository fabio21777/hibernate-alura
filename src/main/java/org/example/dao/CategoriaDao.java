package org.example.dao;

import org.example.model.produto.Categoria;
import org.example.model.produto.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private  EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

}