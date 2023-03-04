package com.datafipe.datafipeweb.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.reflect.TypeToken;
import com.datafipe.datafipeweb.model.Marca;
import com.datafipe.datafipeweb.model.MesReferencia;
import com.datafipe.datafipeweb.model.TipoVeiculo;
import com.datafipe.datafipeweb.model.enums.TipoVeiculoEnum;
import com.datafipe.datafipeweb.repository.MarcaRepository;
import com.datafipe.datafipeweb.repository.MesReferenciaRepository;
import com.google.gson.Gson;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MesReferenciaRepository mesReferenciaRepository;

    @Autowired
    private ApiFipeService apiFipeService;

    public void salvarMarcas() {

        List<MesReferencia> mesesReferencia = mesReferenciaRepository.findAllOrderByIdDesc(PageRequest.of(0, 40));
        List<Marca> marcas = marcaRepository.findAll();

        for (MesReferencia mesReferencia : mesesReferencia) {
            Map<String, String> parametros = new HashMap<>();
            parametros.put("codigoTabelaReferencia", mesReferencia.getId().toString());
            parametros.put("codigoTipoVeiculo", "1");

            String jsonStringResposta = apiFipeService.consultarApi("ConsultarMarcas", parametros);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<Marca>>() {
            }.getType();
            List<Marca> marcasFromApi = gson.fromJson(jsonStringResposta, listType);

            TipoVeiculo tipoVeiculo = new TipoVeiculo();
            tipoVeiculo.setId(TipoVeiculoEnum.CARRO.ordinal() + 1L);
            tipoVeiculo.setTipoVeiculo(TipoVeiculoEnum.CARRO);

            for (Marca marcaFromApi : marcasFromApi) {
                if (!marcas.contains(marcaFromApi)) {
                    marcaFromApi.getMesReferencias().add(mesReferencia);
                    marcaFromApi.setTipoVeiculo(tipoVeiculo);
                    marcas.add(marcaFromApi);
                } else {
                    marcas.get(marcas.indexOf(marcaFromApi)).getMesReferencias().add(mesReferencia);
                    marcas.get(marcas.indexOf(marcaFromApi)).setTipoVeiculo(tipoVeiculo);
                }
            }
        }
        marcaRepository.saveAll(marcas);
    }

}
