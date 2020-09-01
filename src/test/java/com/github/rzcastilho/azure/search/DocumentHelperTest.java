package com.github.rzcastilho.azure.search;

import com.github.rzcastilho.azure.search.exception.EmptyDocumentListException;
import com.github.rzcastilho.azure.search.exception.IndexNotDetectException;
import com.github.rzcastilho.azure.search.exception.NullDocumentException;
import com.github.rzcastilho.azure.search.types.annotation.Index;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentHelperTest {

    @Index("indexName")
    class IndexWithAnnotation { }
    class IndexWithoutAnnotation { }

    @Test
    void resolveIndexFromDocumentShouldReturnIndexName() {
        assertEquals(DocumentHelper.resolveIndexFromDocument(new IndexWithAnnotation()), "indexName");
    }

    @Test
    void resolveIndexFromDocumentShouldReturnIndexNotDetectException() {
        assertThrows(IndexNotDetectException.class, () -> DocumentHelper.resolveIndexFromDocument(new IndexWithoutAnnotation()));
    }

    @Test
    void resolveIndexFromDocumentShouldReturnEmptyDocumentListException() {
        assertThrows(EmptyDocumentListException.class, () -> DocumentHelper.resolveIndexFromDocument(new IndexWithAnnotation[0]));
    }

    @Test
    void resolveIndexFromDocumentShouldReturnNullDocumentException() {
        assertThrows(NullDocumentException.class, () -> DocumentHelper.resolveIndexFromDocument(new IndexWithAnnotation[1]));
    }

    @Test
    void validateDocumentsShouldReturnIndexNotDetectException() {
        assertThrows(IndexNotDetectException.class, () -> DocumentHelper.validateDocuments(new IndexWithoutAnnotation()));
    }

    @Test
    void validateDocumentsShouldReturnEmptyDocumentListException() {
        assertThrows(EmptyDocumentListException.class, () -> DocumentHelper.validateDocuments(new IndexWithAnnotation[0]));
    }

    @Test
    void validateDocumentsShouldReturnNullDocumentException() {
        assertThrows(NullDocumentException.class, () -> DocumentHelper.validateDocuments(new IndexWithAnnotation[1]));
    }

}
