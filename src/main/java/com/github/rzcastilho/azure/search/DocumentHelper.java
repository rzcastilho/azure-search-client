package com.github.rzcastilho.azure.search;

import com.github.rzcastilho.azure.search.exception.EmptyDocumentListException;
import com.github.rzcastilho.azure.search.exception.IndexNotDetectException;
import com.github.rzcastilho.azure.search.exception.NullDocumentException;
import com.github.rzcastilho.azure.search.types.annotation.Index;

public class DocumentHelper {

    private DocumentHelper() {}

    public static String resolveIndexFromDocument(Object... documents) {
        validateDocuments(documents);
        return documents[0].getClass().getAnnotation(Index.class).value();
    }

    public static void validateDocuments(Object... documents) {
        if (documents == null || documents.length == 0) {
            throw new EmptyDocumentListException("The document list is empty.");
        } else {
            if (documents[0] == null) {
                throw new NullDocumentException("The document at list is null.");
            } else {
                Index index = documents[0].getClass().getAnnotation(Index.class);
                if (index == null) {
                    throw new IndexNotDetectException(String.format("It's not possible to determine the index name. " +
                            "Please, check the use of '%s' annotation.", Index.class.getName()));
                }
            }
        }
    }

}
