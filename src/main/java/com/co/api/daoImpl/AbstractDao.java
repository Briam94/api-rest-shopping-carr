package com.co.api.daoImpl;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractDao<T> {
    private Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    

    /******* ACTUALIZAR ********/
    @Transactional
    public void update(T entity) {
    	getEntityManager().merge(entity);
    }

    /****** CREAR ******/
    @Transactional
    public void create(T entity) {
            getEntityManager().persist(entity);
    }

    /******* EDITAR *******/
    @Transactional
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /******************** ELIMINAR *******************/
    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /***************** ENCONTRAR UNO POR ID ************/
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**************** ENCONTRAR TODOS ***************/
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
