package com.datafipe.datafipeweb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datafipe.datafipeweb.model.MesReferencia;

@Repository
@Transactional
public interface MesReferenciaRepository extends CrudRepository<MesReferencia, Long>{

    @Query("select m from MesReferencia m order by m.id desc")
    public Iterable<MesReferencia> findAllOrderByIdDesc();
}
