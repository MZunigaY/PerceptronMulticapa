package com.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.InputEntity;

@Repository
public interface InputRepository extends JpaRepository<InputEntity, Serializable> {

}
