package com.datafipe.datafipeweb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.datafipe.datafipeweb.model.Marca;

@Repository
@Transactional
public interface MarcaRepository extends CrudRepository<Marca, Long> {

    @Query("select m from Marca m order by m.id desc")
    public Iterable<Marca> findAllOrderByIdAsc();
}
