package com.monolithical.carservice.api.endpoints;

import com.monolithical.carservice.api.domain.LeaseContractData;
import com.monolithical.carservice.api.repositories.CarRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.Period;

@RepositoryRestController
public class CarController {

    private CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars/{id}/leaserate")
    ResponseEntity<?> leaseRate(@PathVariable("id") Long id,
                                @RequestParam("interest") BigDecimal interest,
                                @RequestParam("mileage") int mileage,
                                @RequestParam("duration") int duration) {
        var optional = carRepository.findById(id);
        if (optional.isPresent()) {
            var data = new LeaseContractData(optional.get());

            data.setInterestRate(interest);
            data.setMileage(mileage);
            data.setDurationInMonths(Period.ofMonths(duration));

            return ResponseEntity.ok(new LeaseRateResponse(data));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
