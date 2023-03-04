package com.datafipe.datafipeweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.reflect.TypeToken;
import com.datafipe.datafipeweb.model.Marca;
import com.datafipe.datafipeweb.model.MesReferencia;
import com.datafipe.datafipeweb.model.ModeloVeiculo;
import com.datafipe.datafipeweb.model.TipoVeiculo;
import com.datafipe.datafipeweb.model.enums.TipoVeiculoEnum;
import com.datafipe.datafipeweb.repository.MarcaRepository;
import com.datafipe.datafipeweb.repository.MesReferenciaRepository;
import com.datafipe.datafipeweb.repository.ModeloVeiculoRepository;
import com.datafipe.datafipeweb.service.ApiFipeService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@SpringBootTest
class DatafipeTests {

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private MesReferenciaRepository mesReferenciaRepository;

	@Autowired
	private ModeloVeiculoRepository modeloVeiculoRepository;

	@Autowired
	private ApiFipeService consultaApiFipeService;

	@Test
	void salvarTabelaDeReferencia() {

		Map<String, String> parametros = new HashMap<>();

		String jsonStringResposta = consultaApiFipeService.consultarApi("ConsultarTabelaDeReferencia", parametros);
		// Analisa a string JSON usando a biblioteca Gson
		Gson gson = new Gson();
		Type listType = new TypeToken<List<MesReferencia>>() {
		}.getType();
		List<MesReferencia> meses = gson.fromJson(jsonStringResposta, listType);

		mesReferenciaRepository.saveAll(meses);

	}

	@Transactional
	@Test
	void salvarMarcas() {

		List<MesReferencia> mesesReferencia = mesReferenciaRepository.findAllOrderByIdDesc(PageRequest.of(0, 10));
		List<Marca> marcas = marcaRepository.findAll();

		for (MesReferencia mesReferencia : mesesReferencia) {
			Map<String, String> parametros = new HashMap<>();

			parametros.put("codigoTabelaReferencia", mesReferencia.getId().toString());
			parametros.put("codigoTipoVeiculo", "1");

			String jsonStringResposta = consultaApiFipeService.consultarApi("ConsultarMarcas", parametros);

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

	@Test
	void salvarModelos() {
		Iterable<MesReferencia> mesesReferencia = mesReferenciaRepository.findAllOrderByIdDesc(PageRequest.of(0, 2));
		Iterable<Marca> marcas = marcaRepository.findAllOrderByIdDesc(PageRequest.of(0, 2));
		List<ModeloVeiculo> modelosVeiculos = new ArrayList<>();

		modeloVeiculoRepository.findAll()
				.forEach(modelo -> modelosVeiculos.add(modelo));

		for (MesReferencia mesReferencia : mesesReferencia) {
			for (Marca marca : marcas) {
				Map<String, String> parametros = new HashMap<>();

				parametros.put("codigoTabelaReferencia", mesReferencia.getId().toString());
				parametros.put("codigoTipoVeiculo", marca.getTipoVeiculo().getId().toString());
				parametros.put("codigoMarca", marca.getId().toString());

				String jsonStringResposta = consultaApiFipeService.consultarApi("ConsultarModelos", parametros);

				Gson gson = new Gson();
				JsonObject jsonObject = gson.fromJson(jsonStringResposta, JsonObject.class);
				JsonArray jsonArray = jsonObject.getAsJsonArray("Modelos");

				Type listType = new TypeToken<List<ModeloVeiculo>>() {
				}.getType();
				List<ModeloVeiculo> modelosFromApi = gson.fromJson(jsonArray, listType);

				for (ModeloVeiculo modeloVeiculoApi : modelosFromApi) {
					if (modelosVeiculos.contains(modeloVeiculoApi)) {
						modelosVeiculos.get(modelosVeiculos.indexOf(modeloVeiculoApi)).setMarca(marca);
						modelosVeiculos.get(modelosVeiculos.indexOf(modeloVeiculoApi)).getMesesReferencia()
								.add(mesReferencia);
					}else{
						modeloVeiculoApi.setMarca(marca);
						modeloVeiculoApi.getMesesReferencia().add(mesReferencia);
						modelosVeiculos.add(modeloVeiculoApi);
					}
				}
				modeloVeiculoRepository.saveAll(modelosVeiculos);
			}

		}

	}

}
