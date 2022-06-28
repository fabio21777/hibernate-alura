package org.example.dao;

import org.example.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public  List<Produto> buscarProdutosComParametrosCriteria(String nome, BigDecimal valor, LocalDate data){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> root = query.from(Produto.class);
        Predicate and = builder.and();
        if(nome != null && !nome.trim().isEmpty()){

            and = builder.and(and, builder.like(root.get("nome"), "%" + nome + "%"));
        }
        if(valor != null){
            and = builder.and(and, builder.equal(root.get("preco"), valor));
        }
        if(data != null){
            and = builder.and(and, builder.equal(root.get("dataCadastro"), data));
        }

        query.where(and);
        return em.createQuery(query).getResultList();
    }
}