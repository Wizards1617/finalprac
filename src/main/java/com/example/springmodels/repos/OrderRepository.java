package com.example.springmodels.repos;

import com.example.springmodels.models.Feedback;
import com.example.springmodels.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByDate(String Date);

}
