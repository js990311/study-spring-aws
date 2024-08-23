package com.study.aws.opensearch.document;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Document(indexName = "sample-index")
public class SampleDocument {
    @Id
    private Long id;

    @Field
    private String name;

    public SampleDocument(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
