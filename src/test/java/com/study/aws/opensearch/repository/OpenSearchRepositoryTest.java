package com.study.aws.opensearch.repository;

import com.study.aws.opensearch.document.SampleDocument;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class OpenSearchRepositoryTest {

    @Autowired private OpenSearchRepository openSearchRepository;

    @BeforeEach
    public void before(){
        SampleDocument sampleDocument = new SampleDocument(123l, "name");
        openSearchRepository.save(sampleDocument);
    }

    @AfterEach
    public void after(){
        openSearchRepository.deleteById(123l);
    }

    @Test
    void saveAndDelete() {
        SampleDocument sampleDocument = new SampleDocument(1l, "1234");
        openSearchRepository.save(sampleDocument);
        openSearchRepository.deleteById(1L);
    }

    @Test
    void findById() {
        Long id = 123l;
        String name = "name";
        SampleDocument sampleDocs = openSearchRepository.findById(id);
        assertEquals(id, sampleDocs.getId());
        assertEquals(name, sampleDocs.getName());
    }

    @Test
    void findByName(){
        Long id = 123l;
        String name = "name";
        List<SampleDocument> docs = openSearchRepository.findByName(name);
        assertEquals(1, docs.size());
        assertEquals(id, docs.getFirst().getId());
        assertEquals(name, docs.getFirst().getName());
    }
}