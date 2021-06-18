package com.monolithical.carservice.api.repositories;

import com.monolithical.carservice.api.domain.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    List<Car> findAll();
}
