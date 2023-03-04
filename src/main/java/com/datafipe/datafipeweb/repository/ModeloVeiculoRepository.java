package com.datafipe.datafipeweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datafipe.datafipeweb.model.ModeloVeiculo;

@Repository
@Transactional
public interface ModeloVeiculoRepository extends CrudRepository<ModeloVeiculo, Long> {
    @Query("select m from ModeloVeiculo m order by m.id desc")
    public List<ModeloVeiculo> findAllOrderByIdDesc(Pageable pageable);

    public List<ModeloVeiculo> findAll();
}
