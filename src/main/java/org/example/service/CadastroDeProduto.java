package org.example.service;

import org.example.dao.ProdutoDao;
import org.example.model.Produto;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setNome("Xiaomi Redmi");
        produto.setDescricao("Muito legal");
        produto.setPreco(new BigDecimal("800"));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        em.getTransaction().begin();
        dao.cadastrar(produto);
        em.getTransaction().commit();
        em.close();
    }

}
