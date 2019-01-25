package com.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.ResultadoEntity;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoEntity, Serializable> {

}
