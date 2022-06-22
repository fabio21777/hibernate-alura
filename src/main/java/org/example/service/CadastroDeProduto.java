package org.example.service;

import org.example.dao.CategoriaDao;
import org.example.dao.ProdutoDao;
import org.example.model.produto.Categoria;
import org.example.model.produto.Produto;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Categoria categoria = new Categoria("Bebidas");

        Produto produto = new Produto();
        produto.setNome("Xiaomi Redmi");
        produto.setDescricao("Muito legal");
        produto.setPreco(new BigDecimal("800"));
        produto.setCategoria(categoria);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        em.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto);
        em.getTransaction().commit();
        em.close();
    }

}
