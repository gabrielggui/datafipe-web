package com.datafipe.datafipeweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datafipe.datafipeweb.model.ModeloVeiculo;

@Repository
@Transactional
public interface ModeloVeiculoRepository extends CrudRepository<ModeloVeiculo, Long> {

    public List<ModeloVeiculo> findAllByOrderByIdDesc();

    public List<ModeloVeiculo> findAllByOrderByIdDesc(Pageable pageable);

    public List<ModeloVeiculo> findAll();

    public List<ModeloVeiculo> findAll(Pageable pageable);

}
