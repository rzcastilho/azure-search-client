package github.com.rzcastilho.azure.search.types;

public enum Operation {

    UPLOAD("upload", 0),
    DELETE("delete", 1),
    MERGE("merge", 2),
    MERGE_OR_UPLOAD("mergeOrUpload", 3);

    Operation(String name, int value) {
        this.name = name;
        this.value = value;
    }

    String name;
    int value;

}
