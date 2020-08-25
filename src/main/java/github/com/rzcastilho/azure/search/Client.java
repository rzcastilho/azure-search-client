package github.com.rzcastilho.azure.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.com.rzcastilho.azure.search.types.IndexOperation;
import github.com.rzcastilho.azure.search.types.Operation;
import github.com.rzcastilho.azure.search.types.OperationResult;
import kong.unirest.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static github.com.rzcastilho.azure.search.Constants.*;
import static github.com.rzcastilho.azure.search.UrlHelper.*;
import static java.util.jar.Attributes.Name.CONTENT_TYPE;
import static kong.unirest.ContentType.APPLICATION_JSON;

public class Client {

    private final String apiVersion;

    public Client(String hostname, String apiKey, String apiVersion) {
        this.apiVersion = apiVersion;
        Unirest.config()
                .defaultBaseUrl(getBaseUrl(hostname))
                .addDefaultHeader(API_KEY, apiKey);
    }

    public <T> T search(String index, TypeReference<T> type) throws JsonProcessingException {
        String jsonPacket = Unirest.get(getUrlSearch(index))
                .queryString(API_VERSION, apiVersion)
                .queryString(SEARCH, "*")
                .asString()
                .getBody();
        return new ObjectMapper().readValue(jsonPacket, type);
    }

    public OperationResult index(String indexName, Operation operation, Object... documents) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> listDocuments = Arrays.stream(documents)
                .map(document -> {
                    Map<String, Object> map = objectMapper.convertValue(document, new TypeReference<>() {});
                    map.put(SEARCH_ACTION, operation.name());
                    return map;
                })
                .collect(Collectors.toList());
        IndexOperation indexOperation = IndexOperation.builder().build();
        indexOperation.setValue(listDocuments);
        return Unirest.post(getUrlIndex(indexName))
                .queryString(API_VERSION, apiVersion)
                .header(CONTENT_TYPE.toString(), APPLICATION_JSON.getMimeType())
                .body(indexOperation)
                .asObject(OperationResult.class)
                .getBody();
    }

    public OperationResult index(String indexName, Object... documents) {
        return index(indexName, Operation.UPLOAD, documents);
    }

}
