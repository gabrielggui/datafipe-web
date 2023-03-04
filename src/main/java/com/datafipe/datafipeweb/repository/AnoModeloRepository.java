package com.datafipe.datafipeweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datafipe.datafipeweb.model.AnoModelo;

@Repository
@Transactional
public interface AnoModeloRepository extends CrudRepository<AnoModelo, String> {
    public List<AnoModelo> findAll(Pageable pageable);

    public List<AnoModelo> findAll();
}
