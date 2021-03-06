package org.example.dao;

import org.example.model.Categoria;
import org.example.model.CategoriaId;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private  EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        System.out.println("Cadastrando categoria...");
        this.em.persist(categoria);
    }

    public Categoria buscarPorId(CategoriaId id) {
        return this.em.find(Categoria.class, id);
    }
    public void atualizar(Categoria categoria) {
        categoria = em.merge(categoria);
    }
    public void remover(Categoria categoria) {
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }

}