package com.github.rzcastilho.azure.search.types;

public enum Operation {

    UPLOAD("upload"),
    DELETE("delete"),
    MERGE("merge"),
    MERGE_OR_UPLOAD("mergeOrUpload");

    Operation(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    String operationName;

}
