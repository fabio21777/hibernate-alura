package org.example.dao;

import org.example.model.produto.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
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

    public List<Produto> buscarPorNome(String nome) {
        return this.em.createQuery("select p from Produto p where p.nome =  :nome", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }
    public List<Produto> buscarPorNomeCategoria(String nome) {
        return this.em.createQuery("select p from Produto p INNER JOIN Categoria as c on p.categoria = c.id  where c.nome =  :nome", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPeloNome(String nome){
        return this.em.createQuery("select p.preco from Produto p where p.nome =  :nome", BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}