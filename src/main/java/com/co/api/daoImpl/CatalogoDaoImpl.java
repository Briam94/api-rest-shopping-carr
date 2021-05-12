package com.co.api.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.co.api.entity.CatalogoEntity;
import org.apache.log4j.LogManager;

@Repository
public class CatalogoDaoImpl {
	
	 static Logger log = LogManager.getLogger(CatalogoDaoImpl.class.getName());

	@PersistenceContext
    private EntityManager em;
	
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @SuppressWarnings("unchecked")
	public List<CatalogoEntity> findByName(String name) {
    	log.info("Se entra al dao para consumir el metodo findByName con el name = " + name);
    	List<CatalogoEntity> list = new ArrayList<CatalogoEntity>();
    	try {
			Query query = em.createNamedQuery("SELECT * FROM CATALOGO WHERE name LIKE '%:name%'").setParameter(name, name);
			list = query.getResultList();
	    	log.info("Se encuentra " + list.size() + " registros con el nombre: " + name);
		} catch (Exception e) {
	    	log.info("ha ocurrido un error al consumir el metodo findByName con el name = " + name
	    			+ ", el error es: " + e.getMessage());
		}
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<CatalogoEntity> findByRange(Long minRange, Long maxRange){
    	List<CatalogoEntity> list = new ArrayList<CatalogoEntity>();
    	log.info("Se entra al dao para consumir el metodo findByRange con el minRange = " + minRange + " y el maxRange "
    			+ maxRange);
    	try {
			Query query = em.createNativeQuery("SELECT * FROM CATALOGO WHERE precio BETWEEN :minRange AND :maxRange ")
					.setParameter("minRange", minRange).setParameter("maxRange", maxRange);
			list = query.getResultList();
	    	log.info("Se encuentran " + list.size() + "con el minRange = " + minRange + " y el maxRange "
	    			+ maxRange);
		} catch (Exception e) {
	    	log.info("ha ocurrido un error al consumir el metodo findByRange con el minRange = " + minRange + 
	    			" y el maxRange" + maxRange
	    			+ "/n, el error es: " + e.getMessage());
		}
    	return list;    	
    }
    
    @SuppressWarnings("unchecked")
	public List<CatalogoEntity> findByBrand(String brand){
    	log.info("Se entra al dao para consumir el metodo findByBrand con la marca = " + brand);
    	List<CatalogoEntity> list = new ArrayList<CatalogoEntity>();
    	try {
			Query query = em.createNativeQuery("SELECT CATALOGO WHERE marca = :brand").setParameter("brand", brand);
			list = query.getResultList();
	    	log.info("Se encuentran " + list.size() + "con la marca = " + brand);
		} catch (Exception e) {
	    	log.info("ha ocurrido un error al consumir el metodo findByBrand con la marca = " + brand
	    			+ "/n, el error es: " + e.getMessage());
		}
    	return list;
    }
    
    public Long getStock(Long id) {
    	log.info("Se entra al dao para consumir el metodo getStock con el id = " + id);
    	Long stock = 0L;
    	try {
			Query query = em.createNativeQuery("SELECT stock FROM CATALOGO WHERE id = :id").setParameter("id", id);
			stock = (Long) query.getSingleResult();
	    	log.info("la cantidad de stock del porducto con el id " + id + ", es: " + stock);
		} catch (Exception e) {
	    	log.info("ha ocurrido un error al consumir el metodo getStock con el id = " + id
	    			+ "/n, el error es: " + e.getMessage());
		}
    	return stock;
    }
    
    
    public CatalogoEntity getCatalogoEntityById(Long id) {
    	log.info("Se entra al dao para consumir el metodo getCatalogoEntityById con el id = " + id);
		CatalogoEntity catalogo = new CatalogoEntity();
		try {
			Query query = em.createNativeQuery("SELECT * FROM CATALOGO WHERE id = :id").setParameter("id", id);
			catalogo = (CatalogoEntity) query.getSingleResult();
	    	log.info("el producto encontrado con el id " + id + ", es: " + catalogo.getNombre());
		} catch (Exception e) {
	    	log.info("ha ocurrido un error al consumir el metodo getCatalogoEntityById con el id = " + id
	    			+ "/n, el error es: " + e.getMessage());
		}
		return catalogo;
    }
    
    public void updateCatalogo(Long id, Long amount) {
    	log.info("Se entra al dao para consumir el metodo updateCatalogo con el id = " + id + " y la cantidad a descontar es de: " + amount);
    	Query query = em.createNativeQuery("UPDATE CATALOGO SET stock = stock - amount WHERE id = :id")
    			.setParameter("amount", amount).setParameter("id", id);
    	query.executeUpdate();
    }


}
