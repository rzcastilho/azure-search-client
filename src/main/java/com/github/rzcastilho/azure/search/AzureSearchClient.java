package com.github.rzcastilho.azure.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rzcastilho.azure.search.types.Candidato;
import com.github.rzcastilho.azure.search.types.IndexOperation;
import com.github.rzcastilho.azure.search.types.Operation;
import com.github.rzcastilho.azure.search.types.OperationResult;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.jar.Attributes.Name.CONTENT_TYPE;
import static kong.unirest.ContentType.APPLICATION_JSON;

@Slf4j
public class AzureSearchClient {

    private final String apiVersion;

    public AzureSearchClient(String hostname, String apiKey, String apiVersion) {
        this.apiVersion = apiVersion;
        Unirest.config()
                .defaultBaseUrl(UrlHelper.getBaseUrl(hostname))
                .addDefaultHeader(Constants.API_KEY, apiKey)
                .interceptor(new LogInterceptor());
    }

    public <T> T search(String index, TypeReference<T> type) throws JsonProcessingException {
        String jsonPacket = Unirest.get(UrlHelper.getUrlSearch(index))
                .queryString(Constants.API_VERSION, apiVersion)
                .queryString(Constants.SEARCH, "*")
                .asString()
                .getBody();
        return new ObjectMapper().readValue(jsonPacket, type);
    }

    public <T> OperationResult index(String indexName, Operation operation, T... documents) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> listDocuments = Arrays.stream(documents)
                .map(document -> {
                    Map<String, Object> map = objectMapper.convertValue(document, new TypeReference<Map<String, Object>>() {
                    });
                    map.put(Constants.SEARCH_ACTION, operation.getOperationName());
                    return map;
                })
                .collect(Collectors.toList());
        IndexOperation indexOperation = IndexOperation.builder().build();
        indexOperation.setValue(listDocuments);
        return Unirest.post(UrlHelper.getUrlIndex(indexName))
                .queryString(Constants.API_VERSION, apiVersion)
                .header(CONTENT_TYPE.toString(), APPLICATION_JSON.getMimeType())
                .body(indexOperation)
                .asObject(OperationResult.class)
                .getBody();
    }

    public OperationResult index(String indexName, Object... documents) {
        return index(indexName, Operation.UPLOAD, documents);
    }

    public OperationResult index(Operation operation, Object... documents) {
        String indexName = DocumentHelper.resolveIndexFromDocument(documents);
        return index(indexName, operation, documents);
    }

    public OperationResult index(Object... documents) {
        return index(Operation.UPLOAD, documents);
    }

    public static void main(String[] args) {
        Candidato candidato = Candidato
                .builder()
                .id("0-InscricaoId")
                .dtInscricao(Date.from(LocalDateTime.of(2020, 9, 1, 11, 50, 30).atZone(ZoneId.systemDefault()).toInstant()))
                .idConsultor("99")
                .nome("John Doe")
                .cpf("329.517.430-09")
                .rg("20200200x")
                .dtNascimento(Date.from(LocalDateTime.of(2001, 1, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant()))
                .logradouro("Rua dos Bobos")
                .numero("0")
                .bairro("Vinicius de Moraes")
                .cidade("São Paulo")
                .complemento("Casa 1")
                .cep("00000-000")
                .uf("SP")
                .idCurso("607060")
                .curso("Engenharia Civil - Bacharelado")
                .periodoCaptacao("20202")
                .modalidade("Presencial")
                .turno("Noite")
                .marca("ANHANGUERA")
                .dtProva(Date.from(LocalDateTime.of(2020, 9, 5, 8, 0, 0).atZone(ZoneId.systemDefault()).toInstant()))
                .idUnidadeOrigem("712")
                .sistema("OLIMPO")
                .build();
        AzureSearchClient client = new AzureSearchClient("portalk-dev.search.windows.net", "17766ED6D49968B924A68AA7D8815D86", "2020-06-30");
        client.index(candidato);
    }

}
