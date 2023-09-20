package com.example.springmodels.repos;

import com.example.springmodels.models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByModelCar (String ModelCar);
    List<Car> findByModelCarContains(String ModelCar);
    List<Car> findByModelCarContaining(String ModelCar);
    List<Car> findByModelCarIsContaining(String ModelCar);
    List<Car> findByModelCarEquals(String ModelCar);
}
