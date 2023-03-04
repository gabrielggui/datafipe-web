package com.datafipe.datafipeweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.SerializedName;

@Entity
public class ModeloVeiculo {

    @Id
    @SerializedName("Value")
    private Long id;

    @SerializedName("Label")
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Marca marca;

    @ManyToMany
    @JoinTable(name = "modelo_mesreferencia", joinColumns = @JoinColumn(name = "mes_referencia_id"), inverseJoinColumns = @JoinColumn(name = "modelo_veiculo_id"))
    private List<MesReferencia> mesesReferencia = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<MesReferencia> getMesesReferencia() {
        return this.mesesReferencia;
    }

    public void setMesesReferencia(List<MesReferencia> mesesReferencia) {
        this.mesesReferencia = mesesReferencia;
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
        ModeloVeiculo other = (ModeloVeiculo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
