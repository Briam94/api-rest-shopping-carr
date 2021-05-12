package com.co.api.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.co.api.entity.CatalogoEntity;

@Repository
public interface CatalogoDao extends PagingAndSortingRepository<CatalogoEntity, Long>{

}
