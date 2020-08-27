package com.github.rzcastilho.azure.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kong.unirest.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements Interceptor {

    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @SneakyThrows
    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        log.debug("---> Azure Search REST API [REQUEST]: {}", mapper.writeValueAsString(request));
    }

    @SneakyThrows
    @Override
    public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
        log.debug("<--- Azure Search REST API [RESPONSE]: {}", mapper.writeValueAsString(response));
    }

}
