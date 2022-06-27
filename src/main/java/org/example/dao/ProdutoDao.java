package org.example.dao;

import org.example.model.produto.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {

    private  EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        System.out.println("Cadastrando produto...");
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return this.em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        return this.em.createQuery("select p from Produto p", Produto.class).getResultList();
    }

}