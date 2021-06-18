package com.monolithical.carservice.api.endpoints;

import com.monolithical.carservice.api.domain.Car;
import com.monolithical.carservice.api.domain.LeaseContractData;
import com.monolithical.carservice.api.repositories.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;

/**
 * Controller responsible for exposing CRUD functionality over HTTP using JSON
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public List<Car> list() {
        return carRepository.findAll();
    }

    /**
     * Calculates lease rate for given car
     *
     * @param id       car identifier
     * @param interest interest rate in percentage
     * @param mileage  in kilometer per year
     * @param duration in months
     * @return lease rate in euro's
     */
    @GetMapping("/{id}/leaserate")
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

    @GetMapping("/{id}")
    ResponseEntity<?> fetchById(@PathVariable("id") Long id) {
        var optional = carRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Car updatedCar) {
        var optional = carRepository.findById(id);
        if (optional.isPresent()) {
            carRepository.save(optional.get().update(updatedCar));
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        var optional = carRepository.findById(id);
        if (optional.isPresent()) {
            carRepository.delete(optional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
