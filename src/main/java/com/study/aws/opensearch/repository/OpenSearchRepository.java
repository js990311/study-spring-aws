package com.study.aws.opensearch.repository;

import com.study.aws.opensearch.document.SampleDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OpenSearchRepository {
    private final ElasticsearchOperations operations;

    public void save(SampleDocument sampleDocument){
        operations.save(sampleDocument);
    }

    public SampleDocument findById(Long id){
        return operations.get(id.toString(), SampleDocument.class);
    }

    public void deleteById(Long id){
        operations.delete(id.toString(), SampleDocument.class);
    }

    public List<SampleDocument> findByName(String name){
        Criteria criteria = new Criteria("name").is(name);
        Query query = new CriteriaQuery(criteria);
        SearchHits<SampleDocument> search = operations.search(query, SampleDocument.class);
        return search.getSearchHits().stream().map(SearchHit::getContent).toList();
    }
}
