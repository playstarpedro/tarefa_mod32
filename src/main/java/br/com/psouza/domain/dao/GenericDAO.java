package main.java.br.com.psouza.domain.dao;

import java.util.List;
import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;

public abstract class GenericDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ExemploJPA");

    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public T cadastrar(T entity) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
        return entity;
    }

    public void excluir(ID id) {
        EntityManager entityManager = getEntityManager();
        T entity = entityManager.find(entityClass, id);

        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public T consultar(ID id) {
        EntityManager entityManager = getEntityManager();
        T entity = entityManager.find(entityClass, id);
        entityManager.close();
        return entity;
    }

    public void alterar(T entity) {
        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<T> buscarTodos() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);

        TypedQuery<T> tpQuery = entityManager.createQuery(query);
        List<T> list = tpQuery.getResultList();

        entityManager.close();
        return list;
    }
}
