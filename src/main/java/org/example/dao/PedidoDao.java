package org.example.dao;

import org.example.model.Categoria;
import org.example.model.Pedido;

import javax.persistence.EntityManager;

public class PedidoDao {

    private  EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        System.out.println("Cadastrando pedido...");
        this.em.persist(pedido);
    }

    public void atualizar(Pedido pedido) {
        pedido = em.merge(pedido);
    }
    public void remover(Pedido pedido) {
        pedido = em.merge(pedido);
        this.em.remove(pedido);
    }

}