package org.example.service;

import org.example.dao.ProdutoDao;
import org.example.model.produto.Categoria;
import org.example.model.produto.Produto;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setNome("Xiaomi Redmi");
        produto.setDescricao("Muito legal");
        produto.setPreco(new BigDecimal("800"));
        produto.setCategoria(Categoria.CELULARES);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        em.getTransaction().begin();
        dao.cadastrar(produto);
        em.getTransaction().commit();
        em.close();
    }

}
