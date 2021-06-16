package com.monolithical.carservice.api.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

public class LeaseContractData {
    private int mileage;
    private Period duration;
    private BigDecimal interestRatePercent;
    private Car car;

    public LeaseContractData(Car car, int mileage, Period duration, BigDecimal interestRate) {
        this.car = car;
        this.mileage = mileage;
        this.duration = duration;
        this.setInterestRate(interestRate);
    }

    public BigDecimal leaseRate() {
        return null;
    }

    private BigDecimal getMonthlyMileage(){
        return new BigDecimal(mileage).divide(new BigDecimal(12), RoundingMode.HALF_UP);
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRatePercent = interestRate.scaleByPowerOfTen(-2);
    }
}
