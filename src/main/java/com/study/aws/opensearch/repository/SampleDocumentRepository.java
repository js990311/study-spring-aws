package com.study.aws.opensearch.repository;

import com.study.aws.opensearch.document.SampleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

public interface SampleDocumentRepository extends ElasticsearchRepository<SampleDocument, Long> {
}
