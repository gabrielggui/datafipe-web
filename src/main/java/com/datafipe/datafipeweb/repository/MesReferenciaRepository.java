package com.datafipe.datafipeweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datafipe.datafipeweb.model.MesReferencia;

@Repository
@Transactional
public interface MesReferenciaRepository extends CrudRepository<MesReferencia, Long>{

    public List<MesReferencia> findAllByOrderByIdDesc();

    public List<MesReferencia> findAllByOrderByIdDesc(Pageable pageable);
    
    public List<MesReferencia> findAll();

    public List<MesReferencia> findAll(Pageable pageable);
}
