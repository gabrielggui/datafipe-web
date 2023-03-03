package com.datafipe.datafipeweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.annotations.SerializedName;

@Entity
public class MesReferencia {

    @Id
    @SerializedName("Codigo")
    private Long id;

    @Column(length = 20, nullable = false)
    @SerializedName("Mes")
    private String mes;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMes() {
        return this.mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
