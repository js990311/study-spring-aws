package com.study.aws.config;

import org.junit.jupiter.api.Test;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.indices.CreateIndexRequest;
import org.opensearch.client.opensearch.indices.IndexSettings;
import org.opensearch.client.opensearch.indices.PutIndicesSettingsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class OpenSearchConfigTest {
    @Autowired
    private OpenSearchClient client;

    @Test
    public void createIndex() throws IOException {
        String index = "test-index";
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder().index(index).build();
        client.indices().create(createIndexRequest);

        IndexSettings indexSettings = new IndexSettings.Builder().autoExpandReplicas("0-all").build();
        PutIndicesSettingsRequest putIndicesSettingsRequest = new PutIndicesSettingsRequest.Builder().index(index).settings(indexSettings).build();
        client.indices().putSettings(putIndicesSettingsRequest);
    }
}