package com.example.springmodels.repos;

import com.example.springmodels.models.Diller;
import org.springframework.data.repository.CrudRepository;

public interface DillerRepository extends CrudRepository<Diller, Long> {
    Diller findByNameDiller(String NameDiller);
}
