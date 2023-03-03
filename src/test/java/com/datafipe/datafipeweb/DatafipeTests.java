package com.datafipe.datafipeweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
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
import com.datafipe.datafipeweb.service.consultaApiFipeService;
import com.google.gson.Gson;

@SpringBootTest
class DatafipeTests {

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private MesReferenciaRepository mesReferenciaRepository;

	@Autowired
	private consultaApiFipeService consultaApiFipeService;

	@Test
	void salvarTabelaDeReferencia() {

		Map<String, String> parametros = new HashMap<>();

		String jsonStringResposta = consultaApiFipeService.consultarAPI("ConsultarTabelaDeReferencia", parametros);
		// Analisa a string JSON usando a biblioteca Gson
		Gson gson = new Gson();
		Type listType = new TypeToken<List<MesReferencia>>() {
		}.getType();
		List<MesReferencia> meses = gson.fromJson(jsonStringResposta, listType);

		mesReferenciaRepository.saveAll(meses);

	}

	@Test
	void salvarMarcas() {

		Iterable<MesReferencia> mesesReferencia = mesReferenciaRepository.findAllOrderByIdDesc();
		List<Marca> marcas = new ArrayList<>();

		for (Marca marca : marcaRepository.findAll())
			marcas.add(marca);

		for (MesReferencia mesReferencia : mesesReferencia) {
			Map<String, String> parametros = new HashMap<>();

			parametros.put("codigoTabelaReferencia", mesReferencia.getId().toString());
			parametros.put("codigoTipoVeiculo", "1");

			String jsonStringResposta = consultaApiFipeService.consultarAPI("ConsultarMarcas", parametros);

			// Analisa a string JSON usando a biblioteca Gson
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
					
					marcas.add(marcaFromApi);
				} else {
					marcas.get(marcas.indexOf(marcaFromApi)).getMesReferencias().add(mesReferencia);
				}
			}
			marcaRepository.saveAll(marcas);
		}

	}
}
