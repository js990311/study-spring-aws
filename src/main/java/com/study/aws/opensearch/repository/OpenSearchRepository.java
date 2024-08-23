package com.study.aws.opensearch.repository;

import com.study.aws.opensearch.document.SampleDocument;
import lombok.RequiredArgsConstructor;
import org.opensearch.data.core.OpenSearchOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@RequiredArgsConstructor
@Repository
public class OpenSearchRepository {
    private final OpenSearchOperations openSearchOperations;

    public void save(SampleDocument sampleDocument){
        openSearchOperations.save(sampleDocument);
    }

    public SampleDocument findById(Long id){
        return openSearchOperations.get(id.toString(), SampleDocument.class);
    }

    public void deleteById(Long id){
        openSearchOperations.delete(id.toString(), SampleDocument.class);
    }
}
