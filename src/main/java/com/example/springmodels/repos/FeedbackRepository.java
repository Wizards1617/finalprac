package com.example.springmodels.repos;

import com.example.springmodels.models.Diller;
import com.example.springmodels.models.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    Feedback findByDescription(String Description);
}
