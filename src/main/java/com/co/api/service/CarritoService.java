package com.co.api.service;

import java.util.List;

import com.co.api.entity.CarritoEntity;

public interface CarritoService{
		
	public String addProductsToShoppingCarr(String trama);
	
	public boolean checkStock(Long amount, Long stock);
	
	public List<CarritoEntity> getAllShoppingCarr();
	
	public String deleteShoppingCarr();
	
	public String completeBuy(String trama);
	
}
