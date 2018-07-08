package com.piotrslowinski.model.repositories;

import com.piotrslowinski.model.Survey;

public interface SurveyRepository {

    Survey get(Long surveyId);

    void save(Survey survey);
}
