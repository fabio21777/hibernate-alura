package org.example.service;

import org.example.dao.CategoriaDao;
import org.example.model.produto.Categoria;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;

public class CadastroDeProduto {
    public static void main(String[] args) {

        Categoria phoner = new Categoria("PHONER");
        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(phoner);
        categoriaDao.atualizar(phoner);
        phoner.setNome("PHONER X");
        entityManager.flush();
        entityManager.clear();
        categoriaDao.atualizar(phoner);
        phoner.setNome("xiomi");
        entityManager.flush();
        categoriaDao.remover(phoner);
        entityManager.getTransaction().commit();
    }

}
