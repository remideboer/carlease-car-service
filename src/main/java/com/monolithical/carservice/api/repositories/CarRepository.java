package com.monolithical.carservice.api.repositories;

import com.monolithical.carservice.api.domain.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
}
