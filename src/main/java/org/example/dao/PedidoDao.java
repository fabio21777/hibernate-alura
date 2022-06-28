package org.example.dao;

import org.example.model.Categoria;
import org.example.model.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

    public BigDecimal valorTotalVendido() {
        return this.em.createQuery("select sum(p.valorTotal) from Pedido p", BigDecimal.class).getSingleResult();
    }

    public List<Object[]> relatorioDeVendas(){
        String jpql = "SELECT produto.nome, sum(item.quantidade),max(pedido.data)" +
                " FROM Pedido pedido " +
                " JOIN pedido.itens item " +
                " JOIN item.produto produto " +
                " GROUP BY produto.nome " +
                " ORDER BY sum(item.quantidade) DESC ";
        return this.em.createQuery(jpql, Object[].class).getResultList();
    }

}