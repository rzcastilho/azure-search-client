package com.github.rzcastilho.azure.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlHelperTest {

    private static final String HOSTNAME = "azuresearchclient.search.windows.net";
    private static final String INDEXNAME = "indexname";

    private static final String BASE_URL = "https://azuresearchclient.search.windows.net/indexes";
    private static final String URL_SEARCH = "/indexname/docs";
    private static final String URL_INDEX = "/indexname/docs/index";

    @Test
    void shouldReturnBaseUrl() {
        assertEquals(UrlHelper.getBaseUrl(HOSTNAME), BASE_URL);
    }

    @Test
    void shouldReturnUrlSearch() {
        assertEquals(UrlHelper.getUrlSearch(INDEXNAME), URL_SEARCH);
    }

    @Test
    void shouldReturnUrlIndex() {
        assertEquals(UrlHelper.getUrlIndex(INDEXNAME), URL_INDEX);
    }

}
