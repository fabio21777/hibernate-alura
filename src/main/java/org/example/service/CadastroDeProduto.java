package org.example.service;

import org.example.dao.CategoriaDao;
import org.example.dao.ClienteDao;
import org.example.dao.PedidoDao;
import org.example.dao.ProdutoDao;
import org.example.model.*;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Cliente cliente = clienteDao.buscarPorId(1L);
        Produto produto = produtoDao.buscarPorId(1L);




        Pedido pedido = new Pedido(LocalDate.now(),new BigDecimal("50.63"),cliente);
        ItemPedido itemPedido = new ItemPedido(2,pedido,produto);
        pedido.getItens().add(itemPedido);
        em.getTransaction().begin();
        pedidoDao.cadastrar(pedido);
        em.getTransaction().commit();



    }
    public static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Cliente cliente = new Cliente("João", "123456789");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);


        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
