package com.study.aws.opensearch.repository;

import com.study.aws.opensearch.document.SampleDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class OpenSearchRepositoryTest {

    @Autowired private OpenSearchRepository openSearchRepository;

    @Test
    void save() {
        SampleDocument sampleDocument = new SampleDocument(1l, "1234");
        openSearchRepository.save(sampleDocument);
    }

    @Test
    void findById() {
        Long id = 1L;
        String name = "1234";
        SampleDocument sampleDocs = openSearchRepository.findById(id);
        assertEquals(id, sampleDocs.getId());
        assertEquals(name, sampleDocs.getName());
    }

    @Test
    void deleteById(){
        openSearchRepository.deleteById(1L);
    }
}