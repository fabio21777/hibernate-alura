package org.example.service;

import org.example.dao.CategoriaDao;
import org.example.dao.ClienteDao;
import org.example.dao.PedidoDao;
import org.example.dao.ProdutoDao;
import org.example.model.*;
import org.example.util.JPAUtil;
import org.example.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
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
        Cliente cliente2 = clienteDao.buscarPorId(2L);
        Produto produto = produtoDao.buscarPorId(1L);
        Produto produto2 = produtoDao.buscarPorId(2L);
        Produto produto3 = produtoDao.buscarPorId(3L);

        Pedido pedido = new Pedido(LocalDate.now(),new BigDecimal("50.63"),cliente);
        Pedido pedido2 = new Pedido(LocalDate.now(),new BigDecimal("1000.63"),cliente2);
        Pedido pedido3 = new Pedido(LocalDate.now(),new BigDecimal("50.63"),cliente);
        ItemPedido itemPedido = new ItemPedido(2,pedido,produto);
        ItemPedido itemPedido2 = new ItemPedido(1,pedido2,produto2);
        ItemPedido itemPedido3 = new ItemPedido(1,pedido3,produto3);

        pedido.getItens().addAll(Arrays.asList(itemPedido,itemPedido2,itemPedido3));
        em.getTransaction().begin();
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);
        pedidoDao.cadastrar(pedido3);


        BigDecimal valorTotalVendido = pedidoDao.valorTotalVendido();

        System.out.println("Valor Total pedido" +valorTotalVendido);

        List<RelatorioDeVendasVo> relatorioDeVendas = pedidoDao.relatorioDeVendas();

        relatorioDeVendas.forEach(System.out::println);
        em.clear();
        //Pedido pedidoSemCliente = pedidoDao.buscarPorId(2L);
        Pedido pedidoComCliente = pedidoDao.buscarPedidoComCliente(2L);
        em.getTransaction().commit();
        em.close();
        System.out.println("nome do cliente do pedido --buscarPedidoComCliente" + pedidoComCliente.getCliente().getNome());
        //System.out.println("nome do cliente do pedido --buscarPorId---- " + pedidoSemCliente.getCliente().getNome());


    }
    public static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria notebook = new Categoria("NOTEBOOKS");
        Categoria computadores = new Categoria("COMPUTADORES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto notebook1 = new Produto("Macbook Pro", "Muito legal", new BigDecimal("1200"), notebook );
        Produto computador = new Produto("Asus", "Muito legal", new BigDecimal("1000"), computadores );

        Cliente cliente = new Cliente("João", "123456789");
        Cliente cliente2 = new Cliente("Maria", "123456789");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);


        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(notebook);
        categoriaDao.cadastrar(computadores);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(notebook1);
        produtoDao.cadastrar(computador);
        clienteDao.cadastrar(cliente);
        clienteDao.cadastrar(cliente2);

        em.getTransaction().commit();
        em.clear();
        em.close();


    }
}
