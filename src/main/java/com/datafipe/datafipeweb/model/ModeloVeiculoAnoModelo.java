package com.datafipe.datafipeweb.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "modelo_anomodelo")
public class ModeloVeiculoAnoModelo {

    @EmbeddedId
    private ModeloVeiculoAnoModeloKey id;

    @ManyToOne
    @MapsId("modeloVeiculoId")
    @JoinColumn(name = "modelo_veiculo_id")
    private ModeloVeiculo modeloVeiculo;

    @ManyToOne
    @MapsId("anoModeloId")
    @JoinColumn(name = "ano_modelo_id")
    private AnoModelo anoModelo;

    @ManyToOne
    @JoinColumn(name = "mes_referencia_id")
    private MesReferencia mesReferencia;

    public ModeloVeiculo getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(ModeloVeiculo modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public AnoModelo getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(AnoModelo anoModelo) {
        this.anoModelo = anoModelo;
    }

    public MesReferencia getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(MesReferencia mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

}
