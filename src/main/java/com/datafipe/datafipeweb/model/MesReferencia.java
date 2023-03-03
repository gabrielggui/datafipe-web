package com.datafipe.datafipeweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MesReferencia {

    @Id
    private Long id;

    @Column(length = 11, nullable = false)
    private String mes;

    @Column(length = 4, nullable = false)
    private String ano;

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

    public String getAno() {
        return this.ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
