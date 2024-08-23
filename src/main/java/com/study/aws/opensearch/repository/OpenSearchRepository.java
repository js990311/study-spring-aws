package com.study.aws.opensearch.repository;

import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.query_ruleset.GetQueryRulesetRequest;
import com.study.aws.opensearch.document.SampleDocument;
import com.study.aws.s3.dto.FilesDto;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.search.SearchRequestBuilder;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.query_dsl.QueryBuilders;
import org.opensearch.data.client.orhlc.NativeSearchQueryBuilder;
import org.opensearch.data.client.orhlc.OpenSearchRestTemplate;
import org.opensearch.data.core.OpenSearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OpenSearchRepository {
    private final OpenSearchOperations openSearchOperations;
    private final OpenSearchClient openSearchClient;

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
