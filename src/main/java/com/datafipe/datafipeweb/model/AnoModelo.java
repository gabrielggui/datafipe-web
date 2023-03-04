package com.datafipe.datafipeweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.gson.annotations.SerializedName;

@Entity
public class AnoModelo {

    @Id
    @SerializedName("Value")
    private String id;

    @SerializedName("Label")
    private String ano;

    @ManyToMany
    @JoinTable(name = "modelo_anomodelo",
            joinColumns = @JoinColumn(name = "modelo_veiculo_id"),
            inverseJoinColumns = @JoinColumn(name = "ano_modelo_id"))
    private List<ModeloVeiculo> modelosVeiculo = new ArrayList<>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAno() {
        return this.ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public List<ModeloVeiculo> getModelosVeiculo() {
        return this.modelosVeiculo;
    }

    public void setModelosVeiculo(List<ModeloVeiculo> modelosVeiculo) {
        this.modelosVeiculo = modelosVeiculo;
    }
}
