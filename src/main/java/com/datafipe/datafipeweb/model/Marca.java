package com.datafipe.datafipeweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

@Entity
public class Marca {

    @Id
    @SerializedName("Value")
    @Column(nullable = false, unique = true)
    private Long id;

    @SerializedName("Label")
    @Column(nullable = false)
    private String nomeMarca;
    
    @JsonIgnore
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "marca", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ModeloVeiculo> ModeloVeiculo = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMarca() {
        return this.nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

}
