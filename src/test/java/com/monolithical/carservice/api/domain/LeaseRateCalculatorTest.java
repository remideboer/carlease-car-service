package com.monolithical.carservice.api.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Period;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LeaseContractDataTest {
  @Test
  void leaseRate_valid_data() {
    Car car =
        new Car.Builder()
            .make("make")
            .model("model")
            .version("version")
            .doorCount(4)
            .co2Emission(new BigDecimal("122.4"))
            .grossPrice(7500000L)
            .nettPrice(6300000L)
            .build();
    // calculation
    var mileage = 45000;
    var duration = Period.ofMonths(60);
    var interestRate = new BigDecimal("4.5");
    var unit = new LeaseContractData(car);
    unit.setMileage(mileage);
    unit.setInterestRate(interestRate);
    unit.setDuration(duration);
    var expected = new BigDecimal("239.82");

    assertThat(unit.leaseRate(), equalTo(expected));
  }
}
