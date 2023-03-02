package com.datafipe.datafipeweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.reflect.TypeToken;
import com.datafipe.datafipeweb.model.Marca;
import com.datafipe.datafipeweb.repository.MarcaRepository;
import com.google.gson.Gson;

@SpringBootTest
class DatafipeTests {

	@Autowired
	private MarcaRepository marcaRepository;

	String jsonStringResposta;

	@Test
	void conexaoApiFipe() {

		try {
			// URL da API externa que retorna JSON
			String url = "https://veiculos.fipe.org.br/api/veiculos/ConsultarMarcas";

			// Cria um objeto HttpClient
			HttpClient httpClient = HttpClientBuilder.create().build();

			// Cria uma requisição HTTP POST para a URL
			HttpPost requisicao = new HttpPost(url);

			// Define a string JSON que você quer enviar como parâmetro da requisição
			String jsonString = "{\"codigoTabelaReferencia\":\"293\",\"codigoTipoVeiculo\":1}";

			// Cria um objeto StringEntity com a string JSON como argumento
			StringEntity entity = new StringEntity(jsonString);

			// Configura o objeto HttpPost para usar a StringEntity como entidade da
			// requisição
			requisicao.setEntity(entity);

			// Configura o cabeçalho da requisição para indicar que a entidade é do tipo
			// JSON
			requisicao.setHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

			// Executa a requisição e lê a resposta HTTP
			HttpResponse resposta = httpClient.execute(requisicao);
			// Faça algo com a resposta...InputStream conteudo =
			// resposta.getEntity().getContent();
			InputStream conteudo = resposta.getEntity().getContent();

			// Converte o InputStream em uma string
			BufferedReader reader = new BufferedReader(new InputStreamReader(conteudo));
			StringBuilder builder = new StringBuilder();
			String linha;
			while ((linha = reader.readLine()) != null) {
				builder.append(linha);
			}

			jsonStringResposta = builder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	void salvarDados() {
		conexaoApiFipe();

		// Analisa a string JSON usando a biblioteca Gson
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Marca>>() {
		}.getType();
		List<Marca> marcas = gson.fromJson(jsonStringResposta, listType);

		marcaRepository.saveAll(marcas);
	}

}
