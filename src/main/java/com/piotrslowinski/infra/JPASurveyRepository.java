package com.piotrslowinski.infra;

import com.piotrslowinski.model.Survey;
import com.piotrslowinski.model.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class JPASurveyRepository implements SurveyRepository {

    @Autowired
    private EntityManager entityManager;

    public JPASurveyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Survey get(Long surveyId) {
        return entityManager.find(Survey.class, surveyId);
    }

    @Override
    public void save(Survey survey) {
        entityManager.persist(survey);
    }
}
