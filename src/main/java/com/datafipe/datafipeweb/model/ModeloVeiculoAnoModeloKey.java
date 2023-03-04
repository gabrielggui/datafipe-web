package com.datafipe.datafipeweb.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ModeloVeiculoAnoModeloKey implements Serializable {

    private Long modeloVeiculoId;
    private String anoModeloId;

    public Long getModeloVeiculoId() {
        return modeloVeiculoId;
    }

    public void setModeloVeiculoId(Long modeloVeiculoId) {
        this.modeloVeiculoId = modeloVeiculoId;
    }

    public String getAnoModeloId() {
        return anoModeloId;
    }

    public void setAnoModeloId(String anoModeloId) {
        this.anoModeloId = anoModeloId;
    }

}
