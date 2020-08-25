package github.com.rzcastilho.azure.search;

public class UrlHelper {

    private static final String TEMPLATE_BASE_URL = "https://%s/indexes";
    private static final String TEMPLATE_URL_SEARCH = "/%s/docs";
    private static final String TEMPLATE_URL_INDEX = "/%s/docs/index";

    public static String getBaseUrl(String hostname) {
        return String.format(TEMPLATE_BASE_URL, hostname);
    }

    public static String getUrlSearch(String indexName) {
        return String.format(TEMPLATE_URL_SEARCH, indexName);
    }

    public static String getUrlIndex(String indexName) {
        return String.format(TEMPLATE_URL_INDEX, indexName);
    }

}
