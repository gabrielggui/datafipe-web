package com.datafipe.datafipeweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "marca")
    private List<ModeloVeiculo> ModeloVeiculo = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name="marcas_mesreferencia",
        joinColumns = @JoinColumn(name = "marca_id"),
        inverseJoinColumns = @JoinColumn(name = "mes_referencia_id"))
    private List<MesReferencia> mesReferencias = new ArrayList<>();

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

    public TipoVeiculo getTipoVeiculo() {
        return this.tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public List<ModeloVeiculo> getModeloVeiculo() {
        return this.ModeloVeiculo;
    }

    public void setModeloVeiculo(List<ModeloVeiculo> ModeloVeiculo) {
        this.ModeloVeiculo = ModeloVeiculo;
    }

    public List<MesReferencia> getMesReferencias() {
        return this.mesReferencias;
    }

    public void setMesReferencias(List<MesReferencia> mesReferencias) {
        this.mesReferencias = mesReferencias;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Marca other = (Marca) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
