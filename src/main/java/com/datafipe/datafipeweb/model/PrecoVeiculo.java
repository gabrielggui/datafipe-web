package com.datafipe.datafipeweb.model;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.google.gson.annotations.SerializedName;

@Entity
public class PrecoVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("Valor")
    private Double valor;

    @OneToOne
    private ModeloVeiculoAnoModelo modeloVeiculoAnoModelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setValor(String valor) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        
        try {
            this.valor = format.parse(valor).doubleValue();
        } catch (ParseException e) {
            System.out.println("Erro ao converter valor monetário: " + e.getMessage());
        }
    }

    public ModeloVeiculoAnoModelo getModeloVeiculoAnoModelo() {
        return modeloVeiculoAnoModelo;
    }

    public void setModeloVeiculoAnoModelo(ModeloVeiculoAnoModelo modeloVeiculoAnoModelo) {
        this.modeloVeiculoAnoModelo = modeloVeiculoAnoModelo;
    }

}
