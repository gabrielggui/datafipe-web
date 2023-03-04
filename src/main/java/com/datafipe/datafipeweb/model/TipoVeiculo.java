package com.datafipe.datafipeweb.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.datafipe.datafipeweb.enumeration.TipoVeiculoEnum;

@Entity
public class TipoVeiculo {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoVeiculoEnum tipoVeiculo;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVeiculoEnum getTipoVeiculo() {
        return this.tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculoEnum tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

}
