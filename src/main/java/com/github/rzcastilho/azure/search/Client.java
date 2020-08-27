package com.github.rzcastilho.azure.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rzcastilho.azure.search.types.IndexOperation;
import com.github.rzcastilho.azure.search.types.Operation;
import com.github.rzcastilho.azure.search.types.OperationResult;
import kong.unirest.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.jar.Attributes.Name.CONTENT_TYPE;
import static kong.unirest.ContentType.APPLICATION_JSON;

@Slf4j
public class Client {

    private final String apiVersion;

    public Client(String hostname, String apiKey, String apiVersion) {
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

    public OperationResult index(String indexName, Operation operation, Object... documents) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> listDocuments = Arrays.stream(documents)
                .map(document -> {
                    Map<String, Object> map = objectMapper.convertValue(document, new TypeReference<Map<String, Object>>() {});
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

}
