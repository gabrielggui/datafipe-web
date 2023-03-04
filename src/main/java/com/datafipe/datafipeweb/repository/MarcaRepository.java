package com.datafipe.datafipeweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.datafipe.datafipeweb.model.Marca;

@Repository
@Transactional
public interface MarcaRepository extends CrudRepository<Marca, Long> {

    public List<Marca> findAllByOrderByIdDesc();

    public List<Marca> findAllByOrderByIdDesc(Pageable pageable);
    
    public List<Marca> findAll();

    public List<Marca> findAll(Pageable pageable);

}
