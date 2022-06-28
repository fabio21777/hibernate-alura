package org.example.service;

import org.example.dao.CategoriaDao;
import org.example.dao.ProdutoDao;
import org.example.model.Categoria;
import org.example.model.Produto;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        todos.forEach(p2 -> System.out.println("Busca de todos "+p2.getNome()));

        List<Produto> produtosPorNome = produtoDao.buscarPorNome("Xiaomi Redmi");
        produtosPorNome.forEach(p3 -> System.out.println("Busca por nome "+p3.getNome()));

        List<Produto> produtosPorNomeCategoria = produtoDao.buscarPorNomeCategoria("CELULARES");
        produtosPorNomeCategoria.forEach(p4 -> System.out.println("Busca por nome categoria "+p4.getNome()));
        BigDecimal preco = produtoDao.buscarPrecoDoProdutoPeloNome("Xiaomi Redmi");

        System.out.println("Preço do produto: "+preco);

        em.close();
    }
}
