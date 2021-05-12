package com.co.api.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.co.api.entity.CarritoEntity;

@Repository
public interface CarritoDao  extends PagingAndSortingRepository<CarritoEntity, Long>{

}
