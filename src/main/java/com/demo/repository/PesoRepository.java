package com.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.PesoEntity;

@Repository
public interface PesoRepository extends JpaRepository<PesoEntity, Serializable> {
	
	public List<PesoEntity> findAllByOrderByIdAsc();

}
