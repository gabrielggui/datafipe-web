package com.datafipe.datafipeweb.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.datafipe.datafipeweb.model.MesReferencia;
import com.datafipe.datafipeweb.repository.MesReferenciaRepository;
import com.google.gson.Gson;

@Service
public class MesReferenciaService {

    @Autowired
    private ApiFipeService apiFipeService;

    @Autowired
    private MesReferenciaRepository mesReferenciaRepository;

    public String getJsonStringFromApi() {
        Map<String, String> parametros = new HashMap<>();
        return apiFipeService
                .consultarApi("ConsultarTabelaDeReferencia", parametros);
    }

    public void persistDataFromJsonString(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<MesReferencia>>() {
        }.getType();
        List<MesReferencia> mesesReferencia = gson.fromJson(jsonString, listType);

        mesReferenciaRepository.saveAll(mesesReferencia);
    }
}
