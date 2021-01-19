package org.example.api.service;

import org.example.api.domain.ContractTemplate;
import org.example.api.dto.ContractTemplateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class ContractTemplateServiceTest {
    @Autowired
    private ContractTemplateService contractTemplateService;

    @Autowired
    private EntityManager em;

    @Test
    public void testGetOne() {
        ContractTemplate contractTemplate = ContractTemplate.builder()
                .templateName("base1")
                .title("test1")
                .subTitle("subtitle2")
                .build();

        em.persist(contractTemplate);
        em.flush();

        ContractTemplateDto result = contractTemplateService.getContractTemplate(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(contractTemplate.getTemplateName(), result.getTemplateName());
    }
}
