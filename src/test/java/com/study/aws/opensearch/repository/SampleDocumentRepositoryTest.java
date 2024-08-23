package com.study.aws.opensearch.repository;

import com.study.aws.opensearch.document.SampleDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class SampleDocumentRepositoryTest {

    @Autowired
    private SampleDocumentRepository repository;

    @BeforeEach
    public void before(){
        SampleDocument sampleDocument = new SampleDocument(123l, "name");
        repository.save(sampleDocument);
    }

    @AfterEach
    public void after(){
        repository.deleteById(123l);
    }

    @Test
    void saveAndDelete() {
        SampleDocument sampleDocument = new SampleDocument(1l, "1234");
        repository.save(sampleDocument);
        repository.deleteById(1L);
    }

    @Test
    void findById() {
        Long id = 123l;
        String name = "name";
        SampleDocument sampleDocs = repository.findById(id).get();
        assertEquals(id, sampleDocs.getId());
        assertEquals(name, sampleDocs.getName());
    }
}