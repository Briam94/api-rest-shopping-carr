package com.co.api.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.co.api.entity.CarritoEntity;

@Repository
public class CarritoDaoImpl extends AbstractDao<CarritoEntity> {

	 public CarritoDaoImpl() {
		super(CarritoEntity.class);
	}

	static Logger log = Logger.getLogger(CarritoDaoImpl.class.getName());
	 
	 @PersistenceContext
	 private EntityManager em;
	 
	 @Override
	 protected EntityManager getEntityManager() {
		 return em;
	 }

}
